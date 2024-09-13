package com.github.kr328.clash.service.data

fun ImportedDao(): ImportedDao = Database.database.openImportedDao()

fun PendingDao(): PendingDao = Database.database.openPendingDao()

fun SelectionDao(): SelectionDao = Database.database.openSelectionProxyDao()
