-optimizationpasses 5

-adaptclassstrings
-adaptresourcefilenames
-adaptresourcefilecontents
-allowaccessmodification

-obfuscationdictionary proguard-dict.txt
-classobfuscationdictionary proguard-dict.txt
-packageobfuscationdictionary proguard-dict.txt

-repackageclasses com.xhstormr.reversecolor

-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}
