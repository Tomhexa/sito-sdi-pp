package com.example.my_sdi_25.ui.theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventScreen(navController: NavController) {
    var eventName by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }
    var selectedGenre by remember { mutableStateOf("Rock") }
    var budget by remember { mutableStateOf(1000) }

    val datePickerState = rememberDatePickerState()
    val showDatePicker = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crea Nuovo Evento") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Invio dati */ },
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Text("Pubblica Evento")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Campo Nome Evento
            OutlinedTextField(
                value = eventName,
                onValueChange = { eventName = it },
                label = { Text("Nome Evento") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            // Selezione Data
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = eventDate,
                onValueChange = {},
                label = { Text("Data Evento") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker.value = true }) {
                        Icon(Icons.Default.DateRange, "Scegli data")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )

            // DatePicker
            if (showDatePicker.value) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker.value = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                eventDate = datePickerState.selectedDateMillis?.let {
                                    convertMillisToDate(it)
                                } ?: ""
                                showDatePicker.value = false
                            }
                        ) { Text("OK") }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            // Selezione Genere
            Spacer(modifier = Modifier.height(16.dp))
            var expanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedGenre,
                    onValueChange = {},
                    label = { Text("Genere Musicale") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("Rock", "Jazz", "Elettronica", "Pop").forEach { genre ->
                        DropdownMenuItem(
                            text = { Text(genre) },
                            onClick = {
                                selectedGenre = genre
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Selezione Budget
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Budget: €$budget",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Slider(
                value = budget.toFloat(),
                onValueChange = { budget = it.toInt() },
                valueRange = 100f..5000f,
                steps = 49,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}