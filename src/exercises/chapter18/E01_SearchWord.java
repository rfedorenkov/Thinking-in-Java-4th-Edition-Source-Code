package exercises.chapter18;

import net.mindview.util.TextFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static net.mindview.util.Print.print;

/**
 * Exercise 1
 * Modify DirList.java (or one of its variants) so that
 * the FilenameFilter opens and reads each file (using the
 * net.mindview.util.TextFile utility) and accepts the file
 * based on whether any of the trailing arguments on the
 * command line exist in that file.
 * {Args: java E01_SearchWords}
 */
public class E01_SearchWord {
    public static FilenameFilter filter(final String[] words) {
        // Creation of anonymous inner class:
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                Set<String> set = new HashSet<>(
                        new TextFile(new File(dir, name).getAbsolutePath(), "\\W+"));
                for (String word : words)
                    for (String s : set)
                        if (s.equalsIgnoreCase(word))
                            return true;
                return false;
            }
        }; // End of anonymous inner class
    }

    public static void main(String[] args) {
        File path = new File("./src/exercises/chapter18");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(filter(args));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            print(dirItem);
    }
}
/* Output:
E01_SearchWord.java
E02_SortedDirListTest.java
E03_DirSize.java
E04_DirSize2.java
E05_ProcessFiles2.java
E06_ProcessFiles3.java
E07_ReverseReadLines.java
E08_ReadFileFromCommandLine.java
E09_UpperCase.java
E10_FindWords.java
E11_GreenhouseConfig.txt
E11_GreenhouseController2.java
E12_CountLines.java
E13_LineNumber.java
E14_BufferPerformance.java
E15_StoringAndRecoveringData2.java
E16_UsingRandomAccessFile2.java
E17_CharactersInfo.java
E18_TextFile2.java
E19_BytesInfo.java
E20_ClassBeginChecker.java
E21_Echo2.java
E22_OSExecuteDemo2.java
E23_PrintCharBuffer.java
E24_DoubleBufferDemo.java
E25_AllocateDirectTest.java
E26_JGrepMemoryMapped.java
E27_ObjectsSerialization.java
E28_BlipCheck.java
E29_Blip3Test.java
E30_RepairCADState.java
E31_PersonWithAddress.java
E32_WordsCountXML.java
E33_PreferencesDemo2.java
 */