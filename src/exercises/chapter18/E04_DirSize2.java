package exercises.chapter18;

import net.mindview.util.Directory;

import java.io.File;
import java.util.List;

import static net.mindview.util.Print.print;

/**
 * Exercise 4
 * Use Directory.walk() to sum the sizes of all files
 * in a directory tree whose names match a particular
 * regular expression.
 * {Args: ".*\.java"} All java files
 */
public class E04_DirSize2 {
    public static void main(String[] args) {
        Directory.TreeInfo treeInfo;
        if (args.length == 0)
            treeInfo = Directory.walk("./src/exercises/chapter18", ".*\\.java");
        else
            treeInfo = Directory.walk(args[0], ".*\\.java");
        List<File> files = treeInfo.files;
        long totalSize = 0;
        long size;
        for (File file : files) {
            size = file.length();
            print(file + ", " + size + " byte(s)");
            totalSize += size;
        }
        print("------------------------------");
        print("Total: " + files.size() + " file(s), " + totalSize + " bytes");
    }
}
/* Output:
./src/exercises/chapter18/E20_ClassBeginChecker.java, 1120 byte(s)
./src/exercises/chapter18/E23_PrintCharBuffer.java, 1205 byte(s)
./src/exercises/chapter18/E26_JGrepMemoryMapped.java, 1684 byte(s)
./src/exercises/chapter18/E16_UsingRandomAccessFile2.java, 2704 byte(s)
./src/exercises/chapter18/E17_CharactersInfo.java, 1355 byte(s)
./src/exercises/chapter18/E08_ReadFileFromCommandLine.java, 814 byte(s)
./src/exercises/chapter18/E06_ProcessFiles3.java, 1177 byte(s)
./src/exercises/chapter18/E05_ProcessFiles2.java, 1790 byte(s)
./src/exercises/chapter18/E04_DirSize2.java, 1037 byte(s)
./src/exercises/chapter18/E07_ReverseReadLines.java, 1243 byte(s)
./src/exercises/chapter18/E13_LineNumber.java, 936 byte(s)
./src/exercises/chapter18/E09_UpperCase.java, 765 byte(s)
./src/exercises/chapter18/E27_ObjectsSerialization.java, 1820 byte(s)
./src/exercises/chapter18/E25_AllocateDirectTest.java, 10733 byte(s)
./src/exercises/chapter18/E02_SortedDirListTest.java, 1534 byte(s)
./src/exercises/chapter18/E30_RepairCADState.java, 5610 byte(s)
./src/exercises/chapter18/E11_GreenhouseController2.java, 4794 byte(s)
./src/exercises/chapter18/E19_BytesInfo.java, 1336 byte(s)
./src/exercises/chapter18/E33_PreferencesDemo2.java, 1129 byte(s)
./src/exercises/chapter18/E18_TextFile2.java, 2555 byte(s)
./src/exercises/chapter18/E14_BufferPerformance.java, 1296 byte(s)
./src/exercises/chapter18/E22_OSExecuteDemo2.java, 2190 byte(s)
./src/exercises/chapter18/E28_BlipCheck.java, 2471 byte(s)
./src/exercises/chapter18/E01_SearchWord.java, 2433 byte(s)
./src/exercises/chapter18/E32_WordsCountXML.java, 1812 byte(s)
./src/exercises/chapter18/E29_Blip3Test.java, 2086 byte(s)
./src/exercises/chapter18/E03_DirSize.java, 2859 byte(s)
./src/exercises/chapter18/E31_PersonWithAddress.java, 3698 byte(s)
./src/exercises/chapter18/E15_StoringAndRecoveringData2.java, 2275 byte(s)
./src/exercises/chapter18/E24_DoubleBufferDemo.java, 852 byte(s)
./src/exercises/chapter18/E10_FindWords.java, 950 byte(s)
./src/exercises/chapter18/E21_Echo2.java, 817 byte(s)
./src/exercises/chapter18/E12_CountLines.java, 1091 byte(s)
------------------------------
Total: 33 file(s), 70171 bytes
 */