package io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Displays Charsets and aliases.
 */
public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();
        while (it.hasNext()) {
            String csName = it.next();
            printnb(csName);
            Iterator<String> aliases = charSets.get(csName).aliases().iterator();
            if (aliases.hasNext())
                printnb(": ");
            while (aliases.hasNext()) {
                printnb(aliases.next());
                if (aliases.hasNext())
                    printnb(", ");
            }
            print();
        }
    }
}
/* Output:
Big5: csBig5
Big5-HKSCS: big5-hkscs, big5hk, Big5_HKSCS, big5hkscs
CESU-8: CESU8, csCESU-8
EUC-JP: csEUCPkdFmtjapanese, x-euc-jp, eucjis, Extended_UNIX_Code_Packed_Format_for_Japanese, euc_jp, eucjp, x-eucjp
EUC-KR: ksc5601-1987, csEUCKR, ksc5601_1987, ksc5601, 5601, euc_kr, ksc_5601, ks_c_5601-1987, euckr
GB18030: gb18030-2000
GB2312: gb2312, euc-cn, x-EUC-CN, euccn, EUC_CN, gb2312-80, gb2312-1980
GBK: CP936, windows-936
IBM-Thai: ibm-838, ibm838, 838, cp838
IBM00858: PC-Multilingual-850+euro, ibm858, cp858, 858, cp00858, ibm-858, ccsid00858
IBM01140: ibm1140, ebcdic-us-037+euro, cp1140, ibm-1140, 1140, cp01140, ccsid01140
...
...
...
x-windows-50220: cp50220, ms50220
x-windows-50221: cp50221, ms50221
x-windows-874: ms-874, ms874, windows-874
x-windows-949: windows949, ms949, windows-949, ms_949
x-windows-950: ms950, windows-950
x-windows-iso2022jp: windows-iso2022jp
 */