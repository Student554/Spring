package th.co.geniustree.internship.dataclient;

import th.co.geniustree.internship.dataclient.provider.MyData;
import th.co.geniustree.internship.dataclient.provider.MyDataProvider;
import th.co.geniustree.internship.dataclient.provider.MyDataProvider1;
import th.co.geniustree.internship.dataclient.provider.MyDataProvider2;
import th.co.geniustree.internship.dataclient.xx.AutoWired;
import th.co.geniustree.internship.dataclient.xx.Service;

@Service
public class DataClient {
    @AutoWired
    private MyDataProvider dataProviderApi;

    @AutoWired
    private MyDataProvider1 dataProviderApi1;

    @AutoWired
    private MyDataProvider2 dataProviderApi2;

    @AutoWired
    private MyData dataProviderApiTest;

    public void test() {

        System.out.println(dataProviderApi.dataProvider());
        System.out.println(dataProviderApi1.dataProvider());
        System.out.println(dataProviderApi2.dataProvider());
        System.out.println(dataProviderApiTest.dataProvider());

    }

    @Override
    public String toString() {
        return "DataClient{" +
                "dataProviderApi=" + dataProviderApi +
                '}';
    }
}
