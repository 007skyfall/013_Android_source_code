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

include $(CLEAR_VARS)
LOCAL_MODULE:=mylog
LOCAL_MODULE_PATH:=$(LOCAL_PATH)/bin
LOCAL_SRC_FILES:=./src/log.c
LOCAL_SHARED_LIBRARIES:=liblog
include $(BUILD_EXECUTABLE)


include $(CLEAR_VARS)
LOCAL_MODULE:=libhello
LOCAL_MODULE_PATH:=$(LOCAL_PATH)/lib
LOCAL_SRC_FILES:=./src/hello.c
LOCAL_SHARED_LIBRARIES:=liblog
include $(BUILD_SHARED_LIBRARY)






















