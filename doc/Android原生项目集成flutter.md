## Android原生项目集成flutter

### 将flutter项目打包成aar的方式引入
    
    这种方式，如果想要在宿主APP中查看效果，每次都要重新编译，然后复制到指定位置，重新编译宿主APP
    
    1、创建flutter module，此时文件位置在任意位置即可
    2、原生项目支持Java8
    3、将flutter项目编译成aar
        a、直接使用Android studio编译成aar
        b、使用命令行 flutter build aar 将flutter项目编译成aar
    4、在宿主应用程序中修改 app/build.gradle 文件，使其包含本地存储库和上述依赖项：
    
    android {
    // ...
    }

    buildTypes {
        profile {
            initWith debug
        }
    }
    
    repositories {
        maven {
            url 'some/path/my_flutter/build/host/outputs/repo'
            // This is relative to the location of the build.gradle file
            // if using a relative path.
        }
        maven {
            url 'https://storage.googleapis.com/download.flutter.io'
        }
    }
    
    dependencies {
        // ...
        debugImplementation 'com.example.flutter_module:flutter_debug:1.0'
        profileImplementation 'com.example.flutter_module:flutter_profile:1.0'
        releaseImplementation 'com.example.flutter_module:flutter_release:1.0'
    }

### 将flutter项目以module的方式引入

    这种方式如果引入的话需要每个开发者都按照flutter开发环境
    
    1、创建flutter module在项目目录下，与app文件同级
    2、在宿主应用的 settings.gradle 中添加：

    setBinding(new Binding([gradle: this]))
    evaluate(new File(settingsDir.parentFile, 'MultiStudy/flutterlibrary/.android/include_flutter.groovy'))
    /// MultiStudy/flutterlibrary/.android/include_flutter.groovy 为你的flutter module的路径，注意全路径问题

    3、在宿主应用程序中修改 app/build.gradle 文件
    implementation project(':flutter')