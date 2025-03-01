import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.plugin.serialization)
	alias(libs.plugins.kotlin.compose.compiler)
	alias(libs.plugins.ksp)
}

android {
	namespace = "dev.usmon.starter"
	compileSdk = 35

	defaultConfig {
		applicationId = "dev.usmon.starter"
		minSdk = 26
		targetSdk = 35
		versionCode = 1
		versionName = "0.0.1"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables.useSupportLibrary = true
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			signingConfig = signingConfigs.getByName("debug")
		}
	}
	flavorDimensions += "mode"
	productFlavors {
		register("qa") {
			dimension = "mode"
			applicationIdSuffix = ".qa"
			versionNameSuffix = "-dev"
		}
		register("standard") {
			dimension = "mode"
		}
	}

	androidComponents {
		val selector = selector()
			.withBuildType("debug")
			.withFlavor("mode" to "standard")

		beforeVariants(selector) { variant ->
			variant.enable = false
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	buildFeatures {
		compose = true
	}

	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

kotlin {
	compilerOptions {
		jvmTarget = JvmTarget.JVM_11
		freeCompilerArgs.addAll(
			"-Xwhen-guards",
			"-Xnon-local-break-continue",
			"-Xconsistent-data-class-copy-visibility",
			"-Xsuppress-warning=NOTHING_TO_INLINE",
			"-opt-in=kotlin.uuid.ExperimentalUuidApi",
			"-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
			"-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
			"-opt-in=kotlinx.coroutines.FlowPreview",
			"-opt-in=kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi",
		)
	}
}

dependencies {
	implementation(libs.androidx.core)
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.splashscreen)
	implementation(libs.androidx.lifecycle.runtime)
	implementation(libs.androidx.lifecycle.viewmodel)

	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.compose.ui.test.junit4)
	implementation(libs.androidx.compose.material3)
	debugImplementation(libs.androidx.compose.ui.tooling)
	debugImplementation(libs.androidx.compose.ui.test.manifest)

	implementation(libs.kotlinx.serialization.json)

	implementation(libs.google.material)

	implementation(libs.bundles.kotlin.inject.runtime)
	ksp(libs.bundles.kotlin.inject.compiler)

	implementation(libs.squareup.logcat)
}
