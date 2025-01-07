package com.github.kr328.clash.service

import android.content.Context
import com.github.kr328.clash.common.log.Log
import com.github.kr328.clash.core.Clash
import com.github.kr328.clash.core.model.*
import com.github.kr328.clash.service.data.Selection
import com.github.kr328.clash.service.data.SelectionDao
import com.github.kr328.clash.service.remote.IClashManager
import com.github.kr328.clash.service.remote.ILogObserver
import com.github.kr328.clash.service.store.ServiceStore
import com.github.kr328.clash.service.util.sendOverrideChanged
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel

class ClashManager(
    private val context: Context,
) : IClashManager,
    CoroutineScope by CoroutineScope(Dispatchers.IO) {
    private val store = ServiceStore(context)
    private var logReceiver: ReceiveChannel<LogMessage>? = null

    override fun queryTunnelState(): TunnelState = Clash.queryTunnelState()

    override fun queryTrafficTotal(): Long = Clash.queryTrafficTotal()

    override fun queryProxyGroupNames(excludeNotSelectable: Boolean): List<String> = Clash.queryGroupNames(excludeNotSelectable)

    override fun queryProxyGroup(
        name: String,
        proxySort: ProxySort,
    ): ProxyGroup = Clash.queryGroup(name, proxySort)

    override fun queryConfiguration(): UiConfiguration = Clash.queryConfiguration()

    override fun queryProviders(): ProviderList = ProviderList(Clash.queryProviders())

    override fun queryOverride(slot: Clash.OverrideSlot): ConfigurationOverride = Clash.queryOverride(slot)

    override fun patchSelector(
        group: String,
        name: String,
    ): Boolean {
        return Clash.patchSelector(group, name).also {
            val current = store.activeProfile ?: return@also

            if (it) {
                SelectionDao().setSelected(Selection(current, group, name))
            } else {
                SelectionDao().removeSelected(current, group)
            }
        }
    }

    override fun patchOverride(
        slot: Clash.OverrideSlot,
        configuration: ConfigurationOverride,
    ) {
        Clash.patchOverride(slot, configuration)

        context.sendOverrideChanged()
    }

    override fun clearOverride(slot: Clash.OverrideSlot) {
        Clash.clearOverride(slot)
    }

    override suspend fun healthCheck(group: String) = Clash.healthCheck(group).await()

    override suspend fun updateProvider(
        type: Provider.Type,
        name: String,
    ) = Clash.updateProvider(type, name).await()

    override fun setLogObserver(observer: ILogObserver?) {
        synchronized(this) {
            logReceiver?.apply {
                cancel()

                Clash.forceGc()
            }

            if (observer != null) {
                logReceiver =
                    Clash.subscribeLogcat().also { c ->
                        launch {
                            try {
                                while (isActive) {
                                    observer.newItem(c.receive())
                                }
                            } catch (e: CancellationException) {
                                // intended behavior
                                // ignore
                            } catch (e: Exception) {
                                Log.w("UI crashed", e)
                            } finally {
                                withContext(NonCancellable) {
                                    c.cancel()

                                    Clash.forceGc()
                                }
                            }
                        }
                    }
            }
        }
    }
}
