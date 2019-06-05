#include <jni.h>
#include <stdio.h>
#include "cn_com_chioy_opengltest_HelloJNI.h"

JNIEXPORT jstring JNICALL Java_cn_com_chioy_opengltest_HelloJNI_sayHello
(JNIEnv *env, jobject thisObj, jstring name) {
   char buf[128];
   const jbyte *str;
   str = (*env)->GetStringUTFChars(env, name, NULL);
   if (str == NULL) {
   return NULL;
   }
   printf("Hello %s!\n", str);
   (*env)->ReleaseStringUTFChars(env, name, str);
   scanf("%s", buf);
   return (*env)->NewStringUTF(env, buf);
}