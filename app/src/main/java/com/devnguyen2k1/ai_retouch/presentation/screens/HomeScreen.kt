package com.devnguyen2k1.ai_retouch.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devnguyen2k1.ai_retouch.R
import com.devnguyen2k1.ai_retouch.presentation.widgets.FeatureCard
import com.devnguyen2k1.ai_retouch.presentation.widgets.FeatureCardBottom

@Composable
fun HomeScreen(
    onNavigateToRestore: () -> Unit,
    onNavigateToEnhance: () -> Unit,
    onNavigateToRemoveBg: () -> Unit,

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E))
            .padding(vertical = 16.dp), // padding top-bottom
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(16.dp))
        BannerSection()
        Spacer(modifier = Modifier.height(24.dp))
        FeatureGrid(onNavigateToEnhance = onNavigateToEnhance)
        Spacer(modifier = Modifier.height(24.dp))
        BottomFeatureRow(onNavigateToRestore = onNavigateToRestore)
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // padding 2 bên
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "AI Retouch",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logo_pro),
                contentDescription = "Logo_Pro",
                modifier = Modifier.size(width = 78.dp, height = 32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.setting),
                contentDescription = "Logo_Setting",
                modifier = Modifier.size(width = 32.dp, height = 32.dp)
            )
        }
    }
}

@Composable
fun BannerSection() {
    Image(
        painter = painterResource(id = R.drawable.card_1),
        contentDescription = "Card 1",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(250.dp)
    )
}

@Composable
fun FeatureGrid(
    onNavigateToEnhance: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FeatureCard(
                title = "Remove Object",
                iconPainter = painterResource(R.drawable.remove_icon),
                onClick = { /* TODO */ },
                modifier = Modifier.weight(1f)
            )
            FeatureCard(
                title = "Remove Background",
                iconPainter = painterResource(R.drawable.remove_icon),
                onClick = { /* TODO */ },
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FeatureCard(
                title = "Enhance Photo",
                iconPainter = painterResource(R.drawable.enhance_icon),
                onClick = onNavigateToEnhance,
                modifier = Modifier.weight(1f)
            )
            FeatureCard(
                title = "1 Touch Remove",
                iconPainter = painterResource(R.drawable.enhance_icon),
                onClick = { /* TODO */ },
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Composable
fun BottomFeatureRow(onNavigateToRestore: () -> Unit) {
    // TODO: Add bottom features
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            FeatureCardBottom(
                title = "Restore Old Pic",
                iconPainter = painterResource(R.drawable.restore_old_icon),
                onClick = onNavigateToRestore,
                modifier = Modifier.weight(1f)
            )
            FeatureCardBottom(
                title = "Remove Wire",
                iconPainter = painterResource(R.drawable.revoce_wire_icon),
                onClick = {},
                modifier = Modifier.weight(1f)
            )
            FeatureCardBottom(
                title = "Remove Text",
                iconPainter = painterResource(R.drawable.remove_text_icon),
                onClick = {},
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            FeatureCardBottom(
                title = "Cartoon AI",
                iconPainter = painterResource(R.drawable.cartoom_ai_icon),
                onClick = {},
                modifier = Modifier.weight(1f)
            )
            FeatureCardBottom(
                title = "Anime AI",
                iconPainter = painterResource(R.drawable.anime_ai_icon),
                onClick = {},
                modifier = Modifier.weight(1f)
            )
            FeatureCardBottom(
                title = "AI filter",
                iconPainter = painterResource(R.drawable.ai_filter_icon),
                onClick = {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}
