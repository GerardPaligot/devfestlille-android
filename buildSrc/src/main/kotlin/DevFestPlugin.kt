import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class DevFestPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.all(Action {
            when (this) {
                is AppPlugin -> {
                    project.extensions.getByType<BaseAppModuleExtension>().apply {
                        applyCommons()
                    }
                }
                is LibraryPlugin -> {
                    project.extensions.getByType<LibraryExtension>().apply {
                        applyCommons()
                    }
                }
            }
        })
    }
}

fun TestedExtension.applyCommons() {
    compileSdkVersion(Versions.targetSdk)
    defaultConfig.apply {
        targetSdkVersion(Versions.targetSdk)
        minSdkVersion(Versions.minSdk)
        versionCode = 1
        versionName = "1.0"
    }
}
