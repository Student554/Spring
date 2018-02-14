package th.co.geniustree.internship.dataclient;
import th.co.geniustree.internship.api.dataproviderapi.DataProviderApi;
import th.co.geniustree.internship.faris.microioc.container.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MyDataProvider1 implements DataProviderApi {
    @Override
    public List<String> dataProvider() {
        return Arrays.asList("4","5","6");
    }
}

