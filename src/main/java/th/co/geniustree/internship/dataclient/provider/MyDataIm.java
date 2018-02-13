package th.co.geniustree.internship.dataclient.provider;

import th.co.geniustree.internship.dataclient.xx.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MyDataIm implements MyData {

    @Override
    public List<String> dataProvider() {
        return Arrays.asList("10", "11", "12");
    }

}
