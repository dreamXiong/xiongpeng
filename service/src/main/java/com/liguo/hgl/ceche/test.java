package com.liguo.hgl.ceche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class test {
    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

        Map ernieMap2 = new HashMap();
        ernieMap2.put("o33k4xF5yAte0bljTr0CQ2UT3Jfs", -1);
        ernieMap2.put("o33k4xMTLX8sA3d2fnB2dBshD0oI", -1);
        ernieMap2.put("o33k4xIZLe_pk2rj9cDWXKUdmZHI", -1);
        ernieMap2.put("o33k4xI0tBfKSAbDZ6lvCZivA8Is", -1);
        ernieMap2.put("o33k4xNknpdldQx-_LabLhpKiIa0", -1);
        ernieMap2.put(" o33k4xABtitLyFkhMxCZQmvgV-fA", -1);
        ernieMap2.put("o33k4xEUYAMg3QnsoIOSTwxxzck8", -1);
        ernieMap2.put("o33k4xPfNWAvAnunm9Ld3Q9DxDoM", -1);
        ernieMap2.put("o33k4xNOtWUuMd3P-UkSViiG8xrA", -1);
        ernieMap2.put("o33k4xMQow-p4j6Xv1MxWugsQMpg", -1);

        Map<String, String> signInMap = new HashMap<String, String>();
        signInMap.put("1", "21");
        signInMap.put("2", "22");
        signInMap.put("3", "23");
        signInMap.put("4", "24");
        signInMap.put("5", "25");
        //System.out.println(signInMap.get("3"));

        //System.out.println(signInMap.get("2"));
        //System.out.println(signInMap.get("3"));

        List<Integer> signInList = new ArrayList<Integer>();

        signInList.add(11);
        signInList.add(12);
        signInList.add(13);
        signInList.add(14);
        signInList.add(15);
        signInList.add(16);
        signInList.add(17);

        Collection<String> values = signInMap.values();

        ObjectMapper mapper = new ObjectMapper();
        String newShake = mapper.writeValueAsString(signInList);
        String signInUser = mapper.writeValueAsString(values);
        System.out.println("{signInUser:" + signInUser + ",newShake:" + newShake + "}");
        /* List<Integer> subList = signInList.subList(3, signInList.size());
         for (Integer integer : subList) {
             System.out.println(integer);
         }
         System.out.println("M<>>");
         System.out.println(subList.toArray().toString());

         for (Integer integer : signInList) {
             System.out.println(integer);
         }*/

        //System.out.println(ernieMap2.get("o33k4xABtitLyFkhMxCZQmvgV-fA"));

    }
}
