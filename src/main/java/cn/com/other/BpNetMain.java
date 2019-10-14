package cn.com.other;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.util.List;

public class BpNetMain {

    public static void main(String[] args) throws Exception{

        //存入数据库的内容
        String json = "[{\"row\":1,\"list\":[{\"column\":1,\"value\":[0.13516811533783823,0.9757271156016832,0.35673061803461403,0.2790806638332104,0.5273962760840929]},{\"column\":2,\"value\":[0.3873630931237244,0.9140525418541541,0.6206266701588428,0.4231180119771394,0.13499744646079703]},{\"column\":3,\"value\":[0.5356017514511905,0.28372877581215017,0.42674395368369333,0.23083738654726704,0.31194195473158126]}]},{\"row\":2,\"list\":[{\"column\":1,\"value\":[0.4595912999267845,0.16684111904135424,0.7730790998252014,0.9375205553483116,0.2424306008318894,0.8360486597588213,0.008458994998764902,0.09735611039086145]},{\"column\":2,\"value\":[0.3386522468551687,0.8360434350596934,0.35328676274599224,0.2076922312024192,0.09731841678569264,0.6603988661057582,0.3996021622763294,0.5111372268831038]},{\"column\":3,\"value\":[0.2445040809889394,0.6413438313745015,0.2153236723101023,0.660596929902279,0.25199251201621786,0.21084299431076037,0.3107343684499304,0.09134794319044881]},{\"column\":4,\"value\":[0.09986210003386464,0.88208367534982,0.4883761553224789,0.6768870874835308,0.30286824228761433,0.24436841271402643,0.4194007258205815,0.3917322821190298]},{\"column\":5,\"value\":[0.46212495561443645,0.8825767747229744,0.19665192728255299,0.963138703597785,0.27644352382810267,0.2817735390309767,0.3032303901701586,0.7837338009797818]}]}]";
        ObjectMapper mapper = new ObjectMapper();

        //从数据库中取出转化成对象
        /*
        List<BpWeight> l = jsonToBpWeightList(json);
        for(BpWeight bpWeight:l){
            System.out.println(bpWeight.getRow());
            System.out.println(bpWeight.getList());
        }
        */
        //System.out.println(mapper.writeValueAsString(l));

        int[] lens = new int [] {3, 5, 8};
        int layer = 3;
        double[][][] weight = new double [layer-1][][];
        for (int i = 0; i < layer-1; ++ i) {
            weight[i] = new double [lens[i]][];
            for (int j = 0; j < lens[i]; ++ j) {
                weight[i][j] = new double [lens[i+1]];
                for (int k = 0; k < lens[i+1]; ++ k) {
                    weight[i][j][k] = Math.random();
                }
            }
        }
        //List<BpWeight> ll = arrayToBpWeightList(weight);
        //System.out.println("aaa:"+mapper.writeValueAsString(ll));
        //double[][][] newWeight = bpWeightListToArray(ll);

        //List<BpWeight> lll = arrayToBpWeightList(newWeight);
        //System.out.println("bbb:"+mapper.writeValueAsString(lll));




        json = "[\n" +
                "[\n" +
                "  {\"o\":1,\"value\":2},\n" +
                "  {\"o\":3,\"value\":4},\n" +
                "  {\"o\":5,\"value\":6}\n" +
                "],\n" +
                "[\n" +
                "  {\"o\":7,\"value\":8},\n" +
                "  {\"o\":9,\"value\":10},\n" +
                "  {\"o\":11,\"value\":12},\n" +
                "  {\"o\":13,\"value\":14},\n" +
                "  {\"o\":15,\"value\":16}\n" +
                "],\n" +
                "[\n" +
                "  {\"o\":7,\"value\":8},\n" +
                "  {\"o\":9,\"value\":10},\n" +
                "  {\"o\":11,\"value\":12},\n" +
                "  {\"o\":13,\"value\":14},\n" +
                "  {\"o\":15,\"value\":16},\n" +
                "  {\"o\":17,\"value\":18},\n" +
                "  {\"o\":19,\"value\":20},\n" +
                "  {\"o\":21,\"value\":22}\n" +
                "]\n" +
                "]";

        List<List<Neuron>> neuronList = jsonToNeuronList(json);
        String result = neuronListToJson(neuronList);
        System.out.println(result);

        String deta = "[\n" +
                "{\n" +
                "\"row\":1,\n" +
                "\"value\":[1,2,3,4,5]\n" +
                "},\n" +
                "{\n" +
                "\"row\":2,\n" +
                "\"value\":[6,7,8,9]\n" +
                "},\n" +
                "{\n" +
                "\"row\":3,\n" +
                "\"value\":[10,11,12]\n" +
                "}\n" +
                "]";

        List<BpDeta> bpDetas = jsonToBpDetaList(deta);
        double[][] bp =  bpDetaListToArray(bpDetas);
        List<BpDeta> bbbb = arrayTpBpDetaList(bp);
        System.out.println(mapper.writeValueAsString(bbbb));
    }

    /**
     * json转换成BpWeight集合
     * @param json
     * @return
     * @throws Exception
     */
    static List<BpWeight> jsonToBpWeightList(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        //从数据库中取出转化成对象
        List<BpWeight> list = mapper.readValue(json,new TypeReference<List<BpWeight>>() {  });
        return list;
    }


    /**
     * 三位数组转换成BpWeight集合
     * @param weight
     * @return
     * @throws Exception
     */
    static List<BpWeight>  arrayToBpWeightList(double[][][] weight) throws Exception{
        int first = weight.length;
        List<BpWeight> ll = Lists.newArrayList();
        for(int i = 0;i<first;i++){
            BpWeight bpWeight = new BpWeight();
            bpWeight.setRow((i+1));
            List<BpWeightColumn> columns = Lists.newArrayList();
            int second = weight[i].length;
            for(int j=0;j<second;j++){
                BpWeightColumn bpWeightColumn = new BpWeightColumn();
                bpWeightColumn.setColumn((j+1));
                bpWeightColumn.setValue(weight[i][j]);
                /*
                //第三层
                int third = weight[i][j].length;
                double[] value = new double[third];
                for(int k=0;k<third;k++){
                    value[k] =  weight[i][j][k];
                }
                bpWeightColumn.setValue(value);
                */
                columns.add(bpWeightColumn);
            }
            bpWeight.setList(columns);
            ll.add(bpWeight);
        }
        return ll;
    }

    /**
     * BpWeight集合转换成三位数组
     * @param list
     * @return
     * @throws Exception
     */
    public static double[][][] bpWeightListToArray(List<BpWeight> list) throws Exception{
        double[][][] newWeight = new double [list.size()][][];
        int f = newWeight.length;
        for(int i=0;i<f;i++){
            List<BpWeightColumn> columns = list.get(i).getList();
            newWeight[i] = new double[list.get(i).getList().size()][];
            for(int j=0;j<newWeight[i].length;j++){
                BpWeightColumn bpWeightColumn = columns.get(j);
                newWeight[i][j]=bpWeightColumn.getValue();
            }
        }
        return newWeight;
    }


    static List<BpDeta> jsonToBpDetaList(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        //从数据库中取出转化成对象
        List<BpDeta> list = mapper.readValue(json,new TypeReference<List<BpDeta>>() {  });
        return list;
    }

    /**
     * 二位数组转换成BpDeta集合
     * @param deta
     * @return
     * @throws Exception
     */
    static List<BpDeta> arrayTpBpDetaList(double[][] deta) throws Exception{
        int first = deta.length;
        List<BpDeta> bpDetaList = Lists.newArrayList();
        for(int i = 0;i<first;i++){
            BpDeta bpDeta = new BpDeta();
            bpDeta.setRow((i+1));
            bpDeta.setValue(deta[i]);
            bpDetaList.add(bpDeta);
        }
        return bpDetaList;
    }

    /**
     * BpDeta集合转换成二位数组
     * @param list
     * @return
     * @throws Exception
     */
    static double[][] bpDetaListToArray(List<BpDeta> list) throws Exception{
        double[][] deta = new double [list.size()][];
        int f = deta.length;
        for(int i=0;i<f;i++){
            BpDeta bpDeta = list.get(i);
            deta[i] = bpDeta.getValue();
        }
        return deta;
    }

    /**
     * json转List<List<Neuron>>
     * @param json
     * @return
     * @throws Exception
     */
    static List<List<Neuron>> jsonToNeuronList(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        //从数据库中取出转化成对象
        //先转化第一层
        List<Object> list = mapper.readValue(json,new TypeReference<List<Object>>() {  });
        List<List<Neuron>> dataList = Lists.newArrayList();
        for(Object obj:list){
            //再转化第内层
            List<Neuron> l = mapper.readValue(mapper.writeValueAsString(obj),new TypeReference<List<Neuron>>() {  });
            dataList.add(l);
        }
        return dataList;
    }

    /**
     * List<List<Neuron>> 转json
     * @param list
     * @return
     * @throws Exception
     */
    static String neuronListToJson(List<List<Neuron>> list) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }
}
