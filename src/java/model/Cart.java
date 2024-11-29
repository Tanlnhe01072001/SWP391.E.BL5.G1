/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThangNPHE151263
 */
public class Cart {
    List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getQuantityById(String id) {
        return getItemById(id).getQuantity();
    }

    private Item getItemById(String id) {
        for (Item i : items) {
            if (i.getProduct().getProduct_id().equals(id)) {
                return i;
            }
        }
        return null;
    }
    private Item CheckItem(String id, String size, String color) {
        for (Item i : items) {
            if (i.getProduct().getProduct_id().equals(id) && i.size.equals(size) && i.color.endsWith(color)) {
                return i;
            }
        }
        return null;
    }

    public void addItem(Item t) {
        if (getItemById(t.getProduct().getProduct_id()) != null && CheckItem(t.getProduct().getProduct_id(),t.size, t.color) != null) {
            Item m = getItemById(t.getProduct().getProduct_id());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }
    public void updateQuantity(String productId, int newQuantity) {
    for (Item item : items) {
        if (item.getProduct().getProduct_id().equals(productId)) {
            item.setQuantity(newQuantity);
            return;
        }
    }
}

    public void removeItem(String id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public double getTotalMoney() {
        double t = 0;
        for (Item i : items) {
            t += (i.getQuantity() * i.getProduct().product_price);
        }
        return t;
    }
}
