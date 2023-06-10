package rs.ac.metropolitan.cs330_pz

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import rs.ac.metropolitan.cs330_pz.screens.dialogs.RequestInternetPermissionsDialog

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val rule = createComposeRule()
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("rs.ac.metropolitan.cs330_pz", appContext.packageName)
    }

    @Test
    fun testRequestForInternet(){
        rule.setContent {
            val granted = remember {
                mutableStateOf(false)
            }
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                granted.value = isGranted
            }
            RequestInternetPermissionsDialog(launcher = launcher)
        }
        rule.onNodeWithText("Internet Permissions Required")
        rule.onNode(
            hasText("Allow") and hasClickAction()
        ).assertExists()
    }
}