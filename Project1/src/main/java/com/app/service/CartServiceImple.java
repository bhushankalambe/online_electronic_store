package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.AddressRepository;
import com.app.dao.CartRepository;
import com.app.dao.OrderRepository;
import com.app.dao.ProductRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Address;
import com.app.pojos.Cart;
import com.app.pojos.Order;
import com.app.pojos.Product;
import com.app.pojos.Status;
import com.app.pojos.User;

@Service
@Transactional
public class CartServiceImple implements ICartService {

	@Autowired
	private CartRepository dao;
	@Autowired
	private UserRepository userDao;
	@Autowired
	private ProductRepository productDao;
	@Autowired
	private OrderRepository orderDao;
	@Autowired
	private AddressRepository addressDao;

	@Override
	public void addProduct(int pid, int id) {
		User u = userDao.findById(id).orElseThrow(() -> new RuntimeException("Invalid UserId"));
		u.getAddress().size();
		u.getOrder().size();
		Product p = productDao.findById(pid).orElseThrow(() -> new RuntimeException("Not Found"));
		Cart c = dao.findByUserIdAndProducts(u,p);
		if(c != null)
		{
			c.setQuantity(c.getQuantity()+1);
			c.setPrice(c.getQuantity()*c.getPrice());
		}
		else {
			 c = new Cart(1, p.getPrice(), p.getName(), u,p);
			 dao.save(c);
		}
	}

	@Override
	public List<Cart> getCart(int id) {
		User u = userDao.findById(id).orElseThrow(() -> new RuntimeException("Invalid UserId"));
		u.getAddress().size();
		u.getOrder().size();
		return dao.findByUserId(u);
	}
	
	@Override
	public Cart getCartById(int id) {
		Cart c = dao.findById(id).orElseThrow(() -> new RuntimeException("Invalid UserId"));
		return c;
	}
	
	@Override
	public void editCart(Cart c) {
		System.out.println("In edit");
		System.out.println(c.getQuantity());
		Cart cart = dao.findById(c.getId()).orElseThrow(() -> new RuntimeException("Invalid UserId"));
		cart.setPrice(c.getPrice());
		cart.setQuantity(c.getQuantity());
	}
	
	public void removeFromCart(int id)
	{
		Cart c = dao.findById(id).orElseThrow(()-> new RuntimeException("Invalid Id"));
		dao.delete(c);
	}
	
	public void addToOrder(Order o, int id)
	{
		System.out.println(2);
		Address add = addressDao.getById(id);
		User user = userDao.findById(add.getUserId().getId()).orElseThrow(() -> new RuntimeException("Invalid UserId"));
		user.getAddress().size();
		user.getOrder().size();
		List<Cart> c = dao.findByUserId(user);
		List <Product> list = new ArrayList<>();
		for(Cart cart: c)
		{
			list.add(productDao.findById(cart.getProducts().getId()).orElseThrow(()->new RuntimeException("Product Not Found")));
		}
		
		Order order = new Order(o.getAmount(),o.getDeliveryDate(),user,add);
		order.setStat(Status.INPROGRESS);
		order.getList().addAll(list);
		orderDao.save(order);
		c.forEach(i -> dao.delete(i));
	}

	@Override
	public List<Order> getOrderList(int id) {
		User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("Invalid UserId"));
		user.getAddress().size();
		user.getOrder().size();
		List<Order> list = orderDao.findByUser(user);
		list.forEach(i -> i.getList().size());
		return list;
	}

	@Override
	public List<Order> getCompleteOrderList() {
		List<Order> list = orderDao.findAll();
		list.forEach(i -> i.getList().size());
		return list;
	}

	@Override
	public Order getOrder(int id) {
		Order o= orderDao.findById(id).orElseThrow(() -> new RuntimeException("Invalid Order Id"));
		o.getList().size();
		return o;
	}

	@Override
	public void updateOrder(Order o) {
		Order order = orderDao.findById(o.getId()).orElseThrow(() -> new RuntimeException("Invalid Order Id"));
		User u = order.getUser();
		u.getAddress();
		u.getOrder();
		o.setUser(u);
		o.setAddress(order.getAddress());
		orderDao.save(o);
	}

}
