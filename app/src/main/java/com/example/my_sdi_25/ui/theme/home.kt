package com.example.my_sdi_25.ui.theme

@Composable
fun HomeScreen(navController: NavController) {
    val venues = listOf(
        Venue("Jazz Club Bari", "Jazz", 4.5, true),
        Venue("Rock House Lecce", "Rock", 4.2, false)
    )

    LazyColumn {
        items(venues) { venue ->
            VenueCard(venue, onDetailsClick = {
                navController.navigate("venue_details/${venue.id}")
            })
        }
    }
}

@Composable
fun VenueCard(venue: Venue, onDetailsClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onDetailsClick() }
    ) {
        Column {
            AsyncImage(model = venue.imageUrl, contentDescription = null)
            Text(venue.name, style = MaterialTheme.typography.headlineSmall)
            Row {
                Icon(Icons.Default.Star, contentDescription = "Rating")
                Text(venue.rating.toString())
            }
            if (venue.isGreen) {
                GreenBadge()
            }
        }
    }
}