package com.mobileinsider.app.ui.screens

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobileinsider.app.R
import com.mobileinsider.app.ai.AiMessage
import com.mobileinsider.app.ai.AiViewModel
import com.mobileinsider.app.data.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) {

    var visible by
        remember {
            mutableStateOf(false)
        }

    val alpha by
        animateFloatAsState(
            targetValue =
                if (visible) 1f else 0f,

            animationSpec =
                tween(900),

            label = "logo"
        )

    LaunchedEffect(Unit) {

        visible = true

        kotlinx.coroutines.delay(1800)

        onFinished()
    }

    Box(
        modifier =
            Modifier.fillMaxSize(),

        contentAlignment =
            Alignment.Center
    ) {

        Image(

            painter =
                painterResource(
                    R.drawable.mobile_insider_logo
                ),

            contentDescription =
                "Mobile Insider",

            modifier =
                Modifier
                    .size(190.dp)
                    .graphicsLayer {
                        this.alpha = alpha
                    }
        )
    }
}

@Composable
fun HomeScreen(
    open: (String) -> Unit
) {

    LazyColumn(

        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(14.dp)
    ) {

        item {

            Text(
                "Mobile Insider",
                style =
                    MaterialTheme.typography
                        .headlineLarge
            )

            Text(
                "Technology, simplified.",
                color =
                    MaterialTheme.colorScheme
                        .secondary
            )
        }

        item {

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier =
                        Modifier.padding(20.dp)
                ) {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.AutoAwesome,
                            contentDescription =
                                null
                        )

                        Spacer(
                            Modifier.width(10.dp)
                        )

                        Text(
                            "Mobile Insider AI",
                            style =
                                MaterialTheme
                                    .typography
                                    .titleLarge
                        )
                    }

                    Spacer(
                        Modifier.height(8.dp)
                    )

                    Text(
                        "Ask questions, analyze files and get technology help."
                    )

                    Spacer(
                        Modifier.height(12.dp)
                    )

                    Button(
                        onClick = {
                            open("ai")
                        }
                    ) {

                        Text("Open AI")
                    }
                }
            }
        }

        item {

            Text(
                "Explore",
                style =
                    MaterialTheme.typography
                        .titleLarge
            )
        }

        item {

            GridButtons(

                items =
                    listOf(

                        "📰 News" to "news",

                        "▶ Videos" to "videos",

                        "📱 Phones" to "phones",

                        "💻 Laptops" to "laptops",

                        "📷 Cameras" to "cameras",

                        "⚖ Compare" to "compare"
                    ),

                open = open
            )
        }

        item {

            Text(
                "Tools & Support",
                style =
                    MaterialTheme.typography
                        .titleLarge
            )
        }

        item {

            GridButtons(

                items =
                    listOf(

                        "🛠 Tools" to "tools",

                        "🔓 Unlock" to "unlock",

                        "☁ Resources" to "resources",

                        "👥 Team" to "team",

                        "📞 Contact" to "contact"
                    ),

                open = open
            )
        }
    }
}

@Composable
fun DiscoverScreen(
    open: (String) -> Unit
) {

    Page(
        "Discover",
        "Everything from Mobile Insider"
    ) {

        GridButtons(

            listOf(

                "📱 Phones" to "phones",

                "💻 Laptops" to "laptops",

                "📷 Cameras" to "cameras",

                "📰 Tech News" to "news",

                "▶ Videos" to "videos",

                "⚖ Compare" to "compare",

                "☁ Resources" to "resources",

                "👥 Our Team" to "team"
            ),

            open
        )
    }
}

@Composable
fun ToolsScreen(
    open: (String) -> Unit
) {

    Page(
        "Tech Tools",
        "Practical tools for everyday technology"
    ) {

        GridButtons(

            listOf(

                "⚖ Phone Compare" to "compare",

                "🔓 Unlock & Recovery" to "unlock",

                "📱 Device Tools" to "device_tools",

                "💾 Storage Calculator" to "storage",

                "🔋 Battery Tools" to "battery",

                "📶 Network Tools" to "network"
            ),

            open
        )
    }
}

@Composable
fun AiScreen(
    vm: AiViewModel = viewModel()
) {

    var input by
        remember {
            mutableStateOf("")
        }

    val context =
        LocalContext.current

    val stateMessages =
        vm.messages

    val picker =
        rememberLauncherForActivityResult(
            ActivityResultContracts.OpenDocument()
        ) { uri ->

            if (uri == null) return@rememberLauncherForActivityResult

            try {

                val name =
                    context.contentResolver
                        .query(
                            uri,
                            arrayOf(
                                android.provider
                                    .OpenableColumns
                                    .DISPLAY_NAME
                            ),
                            null,
                            null,
                            null
                        )
                        ?.use { cursor ->

                            if (cursor.moveToFirst()) {

                                val index =
                                    cursor.getColumnIndex(
                                        android.provider
                                            .OpenableColumns
                                            .DISPLAY_NAME
                                    )

                                if (index >= 0)
                                    cursor.getString(index)
                                else null

                            } else null
                        }
                        ?: "attachment"

                val mime =
                    context.contentResolver
                        .getType(uri)
                        ?: "application/octet-stream"

                val file =
                    File(
                        context.cacheDir,
                        name
                    )

                context.contentResolver
                    .openInputStream(uri)
                    ?.use { input ->

                        file.outputStream()
                            .use { output ->

                                input.copyTo(output)
                            }
                    }

                vm.selectFile(
                    file,
                    mime
                )

            } catch (_: Exception) {
            }
        }

    Scaffold(

        topBar = {

            SmallTopAppBar(

                title = {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.AutoAwesome,
                            contentDescription =
                                null
                        )

                        Spacer(
                            Modifier.width(8.dp)
                        )

                        Text(
                            "Mobile Insider AI"
                        )
                    }
                },

                actions = {

                    IconButton(
                        onClick = vm::clear
                    ) {

                        Icon(
                            Icons.Default.Delete,
                            contentDescription =
                                "New chat"
                        )
                    }
                }
            )
        }

    ) { padding ->

        Column(

            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .navigationBarsPadding()
        ) {

            if (
                stateMessages.isEmpty()
            ) {

                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(24.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        "✦",
                        style =
                            MaterialTheme
                                .typography
                                .displayMedium
                    )

                    Text(
                        "Your technology assistant",
                        style =
                            MaterialTheme
                                .typography
                                .headlineSmall
                    )

                    Spacer(
                        Modifier.height(8.dp)
                    )

                    Text(
                        "Ask anything about phones, laptops, apps, AI, coding and technology."
                    )
                }
            }

            LazyColumn(

                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),

                contentPadding =
                    PaddingValues(12.dp),

                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                items(
                    stateMessages,
                    key = { it.id }
                ) { message ->

                    MessageBubble(message)
                }

                if (vm.loading) {

                    item {

                        Row(
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            CircularProgressIndicator(
                                modifier =
                                    Modifier.size(22.dp)
                            )

                            Spacer(
                                Modifier.width(10.dp)
                            )

                            Text(
                                "AI is thinking..."
                            )
                        }
                    }
                }

                vm.error?.let { error ->

                    item {

                        Text(
                            error,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .error
                        )
                    }
                }
            }

            vm.selectedFile?.let { file ->

                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 12.dp,
                                vertical = 4.dp
                            )
                ) {

                    Row(
                        modifier =
                            Modifier.padding(10.dp),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Text(
                            "📎 ${file.name}",
                            modifier =
                                Modifier.weight(1f)
                        )

                        IconButton(
                            onClick =
                                vm::removeFile
                        ) {

                            Icon(
                                Icons.Default.Close,
                                contentDescription =
                                    "Remove"
                            )
                        }
                    }
                }
            }

            Row(

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp),

                verticalAlignment =
                    Alignment.Bottom
            ) {

                IconButton(

                    enabled =
                        !vm.loading,

                    onClick = {

                        picker.launch(
                            arrayOf(
                                "image/*",
                                "application/pdf",
                                "text/*",
                                "application/json"
                            )
                        )
                    }
                ) {

                    Icon(
                        Icons.Default.AttachFile,
                        contentDescription =
                            "Attach"
                    )
                }

                OutlinedTextField(

                    value =
                        input,

                    onValueChange = {
                        input = it
                    },

                    modifier =
                        Modifier.weight(1f),

                    enabled =
                        !vm.loading,

                    placeholder = {
                        Text(
                            "Ask Mobile Insider AI..."
                        )
                    },

                    maxLines = 5,

                    shape =
                        RoundedCornerShape(24.dp)
                )

                Spacer(
                    Modifier.width(6.dp)
                )

                IconButton(

                    enabled =
                        !vm.loading &&
                            (
                                input.isNotBlank() ||
                                vm.selectedFile != null
                            ),

                    onClick = {

                        vm.send(input)

                        input = ""
                    }
                ) {

                    Icon(
                        Icons.Default.Send,
                        contentDescription =
                            "Send"
                    )
                }
            }
        }
    }
}

@Composable
private fun MessageBubble(
    message: AiMessage
) {

    Row(

        modifier =
            Modifier.fillMaxWidth(),

        horizontalArrangement =
            if (message.role == "user")
                Arrangement.End
            else
                Arrangement.Start
    ) {

        Card(

            modifier =
                Modifier.fillMaxWidth(0.88f),

            shape =
                RoundedCornerShape(18.dp)
        ) {

            Column(
                modifier =
                    Modifier.padding(14.dp)
            ) {

                Text(
                    if (
                        message.role == "user"
                    )
                        "You"
                    else
                        "✦ Mobile Insider AI",

                    style =
                        MaterialTheme
                            .typography
                            .labelMedium
                )

                Spacer(
                    Modifier.height(6.dp)
                )

                if (
                    message.attachmentName
                        != null
                ) {

                    Text(
                        "📎 ${message.attachmentName}"
                    )

                    Spacer(
                        Modifier.height(6.dp)
                    )
                }

                Text(
                    message.text
                )

                if (
                    !message.imageBase64
                        .isNullOrBlank()
                ) {

                    Spacer(
                        Modifier.height(10.dp)
                    )

                    try {

                        val bytes =
                            Base64.decode(
                                message.imageBase64,
                                Base64.DEFAULT
                            )

                        val bitmap =
                            BitmapFactory
                                .decodeByteArray(
                                    bytes,
                                    0,
                                    bytes.size
                                )

                        if (bitmap != null) {

                            Image(

                                bitmap =
                                    bitmap
                                        .asImageBitmap(),

                                contentDescription =
                                    "AI image",

                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            RoundedCornerShape(
                                                14.dp
                                            )
                                        )
                            )
                        }

                    } catch (_: Exception) {
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {

    Page(
        "Profile",
        "Your Mobile Insider account"
    ) {

        Text(
            "Login",
            style =
                MaterialTheme
                    .typography
                    .titleLarge
        )

        Spacer(
            Modifier.height(12.dp)
        )

        var email by
            remember {
                mutableStateOf("")
            }

        var password by
            remember {
                mutableStateOf("")
            }

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier =
                Modifier.fillMaxWidth(),
            label = {
                Text("Email")
            }
        )

        Spacer(
            Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier =
                Modifier.fillMaxWidth(),
            label = {
                Text("Password")
            }
        )

        Spacer(
            Modifier.height(12.dp)
        )

        Button(
            onClick = {
                // Supabase authentication
                // connection will use the same
                // backend project as the website.
            }
        ) {

            Text("Login")
        }

        Spacer(
            Modifier.height(16.dp)
        )

        Text(
            "Mobile Insider account services use the same backend architecture as the website."
        )
    }
}

@Composable
fun NewsScreen() {

    var result by
        remember {
            mutableStateOf(
                "Loading Mobile Insider news..."
            )
        }

    val scope =
        rememberCoroutineScope()

    LaunchedEffect(Unit) {

        scope.launch {

            try {

                val response =
                    withContext(
                        Dispatchers.IO
                    ) {

                        ApiClient.request(
                            ApiClient.NEWS
                        )
                    }

                result =
                    response.toString(2)

            } catch (e: Exception) {

                result =
                    "News service is currently unavailable."
            }
        }
    }

    Page(
        "Tech News",
        "Mobile Insider news"
    ) {

        Text(result)
    }
}

@Composable
fun VideosScreen() {

    Page(
        "Videos",
        "Mobile Insider video content"
    ) {

        FeatureText(
            "▶️",
            "YouTube Videos",
            "Latest Mobile Insider videos and creator content."
        )
    }
}

@Composable
fun CompareScreen() {

    var phone1 by
        remember {
            mutableStateOf("")
        }

    var phone2 by
        remember {
            mutableStateOf("")
        }

    Page(
        "Phone Compare",
        "Compare two smartphones"
    ) {

        OutlinedTextField(
            phone1,
            { phone1 = it },
            Modifier.fillMaxWidth(),
            label = {
                Text("Phone 1")
            }
        )

        Spacer(
            Modifier.height(10.dp)
        )

        OutlinedTextField(
            phone2,
            { phone2 = it },
            Modifier.fillMaxWidth(),
            label = {
                Text("Phone 2")
            }
        )

        Spacer(
            Modifier.height(12.dp)
        )

        Button(
            onClick = {}
        ) {

            Text("Compare Phones")
        }

        Spacer(
            Modifier.height(12.dp)
        )

        Text(
            "The native comparison screen is ready to connect to the existing Mobile Insider phone comparison data."
        )
    }
}

@Composable
fun UnlockScreen() {

    Page(
        "Phone Unlock & Recovery",
        "Troubleshooting and recovery tools"
    ) {

        FeatureText(
            "🔓",
            "Recovery",
            "Access Mobile Insider's phone recovery and troubleshooting resources."
        )

        Spacer(
            Modifier.height(12.dp)
        )

        Text(
            "Only legitimate device recovery and account-owner procedures should be used."
        )
    }
}

@Composable
fun ResourcesScreen() {

    Page(
        "Resources",
        "Useful Mobile Insider files and resources"
    ) {

        FeatureText(
            "☁️",
            "Mobile Insider Resources",
            "Access resources provided through the Mobile Insider backend."
        )
    }
}

@Composable
fun TeamScreen() {

    Page(
        "Our Team",
        "Meet the Mobile Insider team"
    ) {

        FeatureText(
            "👥",
            "Mobile Insider",
            "Technology content, tools and services from Mobile Insider."
        )
    }
}

@Composable
fun ContactScreen() {

    Page(
        "Contact",
        "Get in touch with Mobile Insider"
    ) {

        FeatureText(
            "📞",
            "Contact Mobile Insider",
            "Use the official Mobile Insider contact channels for support and enquiries."
        )
    }
}

@Composable
fun CategoryScreen(
    title: String,
    icon: String,
    description: String
) {

    Page(
        "$icon $title",
        description
    ) {

        Text(
            "Native Mobile Insider section"
        )

        Spacer(
            Modifier.height(10.dp)
        )

        Text(
            "This screen is independent from the website UI. Website data can be connected here through the existing backend/database."
        )
    }
}

@Composable
private fun Page(
    title: String,
    subtitle: String,
    content:
        @Composable
        ColumnScope.() -> Unit
) {

    Column(

        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp)
    ) {

        Text(
            title,
            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Spacer(
            Modifier.height(4.dp)
        )

        Text(
            subtitle,
            color =
                MaterialTheme
                    .colorScheme
                    .secondary
        )

        Spacer(
            Modifier.height(18.dp)
        )

        content()
    }
}

@Composable
private fun FeatureText(
    icon: String,
    title: String,
    description: String
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {

            Text(
                "$icon  $title",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            Spacer(
                Modifier.height(8.dp)
            )

            Text(description)
        }
    }
}

@Composable
private fun GridButtons(
    items: List<Pair<String, String>>,
    open: (String) -> Unit
) {

    Column(
        verticalArrangement =
            Arrangement.spacedBy(8.dp)
    ) {

        items
            .chunked(2)
            .forEach { row ->

                Row(

                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.spacedBy(8.dp)
                ) {

                    row.forEach { item ->

                        Button(

                            onClick = {
                                open(item.second)
                            },

                            modifier =
                                Modifier.weight(1f)
                        ) {

                            Text(
                                item.first
                            )
                        }
                    }

                    if (row.size == 1) {

                        Spacer(
                            Modifier.weight(1f)
                        )
                    }
                }
            }
    }
}
