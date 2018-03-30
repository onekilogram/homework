package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.koala.common.ResultDTO;
import com.netease.koala.dao.ItemDao;
import com.netease.koala.dao.RecordDao;
import com.netease.koala.dao.ShopCarDao;
import com.netease.koala.dao.UserDao;
import com.netease.koala.model.Item;
import com.netease.koala.model.Record;
import com.netease.koala.model.ShopCar;
import com.netease.koala.model.ShopCarExtend;
import com.netease.koala.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class UserMapperTest {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private RecordDao recordDao;
	
	@Autowired
	private ShopCarDao shopCarDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDeleteByPrimaryKey() {
	}

	@Test
	public void testInsert() {
	}

	@Test
	public void testInsertSelective() {
	}

	@Test
	public void testSelectByPrimaryKey() {
//		System.out.println("OK");
//		User user = userDao.selectByPrimaryKey(2);
//		System.out.println(user.getPassword());
//		System.out.println("GG");
//		System.out.println("GG");
//		//System.out.println(userDao.selectByUserName("buyer").getPassword());
//		
//		Item list1 = itemDao.selectByPrimaryKey(1);
//		System.out.println(list1.getTitle());
//		
//		List<Item> list2 = itemDao.selectHaveItemByUserId(1);
//		System.out.println(list2.size());
//		
//		List<Item> list3 = itemDao.selectNoHaveItemByUserId(1);
//		System.out.println(list3.size());
//		
//		List<Record> list5 = recordDao.selectOneRecord(1, 1);
//		System.out.println(list5.size());
//		
//		List<Record> list = recordDao.selectAllRecord(1);
//		System.out.println(list.size());
//
//		ShopCar shopCar =  shopCarDao.selectByUseridAndItemid(1, 7);
		
//		List<ShopCarExtend> list = recordDao.selectRecordEx(1);
//		for(ShopCarExtend shopCarExtend : list){
//			System.out.println(shopCarExtend.getIcon());
//		}
		
		itemDao.updateItemCount(1, 5,3);
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
	}

	@Test
	public void testUpdateByPrimaryKey() {
	}

}
