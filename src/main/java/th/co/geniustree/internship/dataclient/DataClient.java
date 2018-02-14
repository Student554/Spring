package th.co.geniustree.internship.dataclient;

import th.co.geniustree.internship.api.dataproviderapi.DataProviderApi;
import th.co.geniustree.internship.faris.microioc.container.AutoWired;
import th.co.geniustree.internship.faris.microioc.container.Service;

@Service
public class DataClient {
    @AutoWired
    private MyDataProvider dataProviderApi;

    @AutoWired
    private MyDataProvider1 dataProviderApi1;

    @AutoWired
    private DataProviderApi dataProviderApi2;

    public void microioc() {

        System.out.println(dataProviderApi.dataProvider());
        System.out.println(dataProviderApi1.dataProvider());
        System.out.println(dataProviderApi2.dataProvider());

    }

    @Override
    public String toString() {
        return "DataClient{" +
                "dataProviderApi=" + dataProviderApi +
                '}';
    }
}
