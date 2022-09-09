package com.hfad.whatsappui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hfad.whatsappui.ui.theme.WhatsAppUITheme
import com.hfad.whatsappui.ui.theme.WhatsappGreenDark
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


class HomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {

                WhatsAppUITheme() {
                    val systemUiController = rememberSystemUiController()
                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = WhatsappGreenDark
                        )
                    }
                    Surface {
                        HomeScreenContent()
                    }
                }
            }
        }
    }
}


@Composable
fun HomeScreenContent() {
    FAB()
    val state = rememberCollapsingToolbarScaffoldState()
    Column {

        CollapsingToolbarScaffold(modifier = Modifier.fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.EnterAlways,
            toolbar = {
                ToolBar()
            }) {
            Column {

                Row {
                    Tabs()
                }
                Row {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(100) {
                            Text(
                                text = "Item $it",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ToolBar() {
    TopAppBar(
        modifier = Modifier.height(55.dp),
        title = { Text("WhatsApp", fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        actions = {
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search icon",
                        tint = Color.White
                    )

                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "search icon",
                        tint = Color.White
                    )
                }
            }
        }
    )
}

@Composable
fun Tabs() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("CZATY", "STATUS", "POL")
    Column {
        TabRow(backgroundColor = WhatsappGreenDark, selectedTabIndex = tabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) })
            }
        }
    }
}

@Composable
fun FAB() {
    Box(modifier = Modifier.fillMaxSize()) {

        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            shape = CircleShape,
            backgroundColor = WhatsappGreenDark,
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        ) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "Start Chat")
        }
    }
}

