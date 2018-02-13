package th.co.geniustree.internship.dataclient.provider;
import th.co.geniustree.internship.api.dataproviderapi.DataProviderApi;
import th.co.geniustree.internship.dataclient.xx.Service;

import java.util.List;

@Service
public interface MyData extends DataProviderApi{

    List<String> dataProvider();
}
