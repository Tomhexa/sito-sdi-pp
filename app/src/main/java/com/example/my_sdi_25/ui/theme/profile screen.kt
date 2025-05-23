
class profile screen {
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Il mio Profilo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Sezione Info Utente
            Card(
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Mario Rossi",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("🎤 Artista | Genere: Rock")
                    Text("⭐ Rating: 4.8/5")
                }
            }

            // Sezione Statistiche
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Statistiche",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                StatCard("Eventi", "45")
                StatCard("Fan", "1.2K")
                StatCard("Green Stage", "8")
            }

            // Sezione Impostazioni
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Impostazioni",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            ListItem(
                headlineContent = { Text("Abbonamento Premium") },
                trailingContent = { Switch(checked = true, onCheckedChange = {}) }
            )
            ListItem(
                headlineContent = { Text("Notifiche") },
                trailingContent = { Badge("12") }
            )
        }
    }
}

@Composable
fun StatCard(label: String, value: String) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(value, style = MaterialTheme.typography.headlineMedium)
            Text(label, color = MaterialTheme.colorScheme.onTertiaryContainer)
        }
    }
}