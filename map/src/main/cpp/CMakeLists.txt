cmake_minimum_required(VERSION 3.10.2)



set_target_properties(jniLibs PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/src/main/jniLibs/arm64-v8a/libDaumMapEngineApi.so)
set_target_properties(jniLibs PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/src/main/jniLibs/armeabi/libDaumMapEngineApi.so)
set_target_properties(jniLibs PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/src/main/jniLibs/armeabi-v7a/libDaumMapEngineApi.so)

