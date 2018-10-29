# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.cpp )

add_library( # Sets the name of the library.
             Complejo

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/Complejo.cpp )

# Specifies a path to native header files.
include_directories(src/main/cpp/Complejo.h)


add_library( # Sets the name of the library.
             aplicarComplejo

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/aplicarComplejo.cpp )
add_library( # Sets the name of the library.
             multComplexI

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/multComplexI.cpp )
add_library( # Sets the name of the library.
             multComplexR

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/multComplexR.cpp )
add_library( # Sets the name of the library.
             filter

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/filter.cpp )
add_library( # Sets the name of the library.
             metodoLPC

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/metodoLPC.cpp )
add_library( # Sets the name of the library.
        matrizOLA

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/matrizOLA.cpp )
add_library( # Sets the name of the library.
        pressStack

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/pressStack.cpp )
# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
target_link_libraries( # Specifies the target library.
                       Complejo

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
target_link_libraries( # Specifies the target library.
                       aplicarComplejo

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )

target_link_libraries( # Specifies the target library.
                       multComplexI

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
target_link_libraries( # Specifies the target library.
                       multComplexR

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
target_link_libraries( # Specifies the target library.
                        filter

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
target_link_libraries( # Specifies the target library.
                        metodoLPC

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
target_link_libraries( # Specifies the target library.
        matrizOLA

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )
target_link_libraries( # Specifies the target library.
        pressStack

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )