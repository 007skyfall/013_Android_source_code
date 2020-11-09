LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE   :=libadd
LOCAL_SRC_FILES:= ./src/add.c
LOCAL_MODULE_PATH:=$(LOCAL_PATH)/lib
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE   :=libadd
LOCAL_SRC_FILES:= ./src/add.c
LOCAL_MODULE_PATH:=$(LOCAL_PATH)/lib
include $(BUILD_STATIC_LIBRARY)


include $(CLEAR_VARS)
LOCAL_MODULE:=test
LOCAL_MODULE_PATH:=$(LOCAL_PATH)/bin
LOCAL_SRC_FILES:=./src/test.c
#LOCAL_LDFLAGS:=	-L ./lib -ladd
LOCAL_LDFLAGS:=	$(LOCAL_PATH)/lib/libadd.so
include $(BUILD_EXECUTABLE)


