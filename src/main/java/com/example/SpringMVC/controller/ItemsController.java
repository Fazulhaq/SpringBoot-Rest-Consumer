package com.example.SpringMVC.controller;

import com.example.SpringMVC.model.Items;
import com.example.SpringMVC.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inventory")
public class ItemsController {
    @Autowired
    ItemsService its;
    @RequestMapping(value = {"/all","/"})
    public String getAll(Model model){
        List<Items> items = its.getAll();
        model.addAttribute("items", items);
        model.addAttribute("heading", "List of All Products");
        return "itemsView";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Items i = its.get(id);
        model.addAttribute("item", i);
        model.addAttribute("heading", "Edit Items");
        return "editView";
    }
    @RequestMapping("/update")
    public String update(Model model, Items items){
        its.update(items);
        List<Items> item= its.getAll();
        model.addAttribute("items", item);
        model.addAttribute("heading", "List of Products");
        return "redirect:/inventory/all";
    }
    @RequestMapping("/add")
    public String add(Model model){
        Items i = new Items();
        model.addAttribute("item", i);
        model.addAttribute("heading", "Item Added");
        return "editView";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){
        Items it = its.get(id);
        its.delete(it);
        List<Items> items = its.getAll();
        model.addAttribute("item", items);
        model.addAttribute("heading", "List of Products");
        return "redirect:/inventory/all";
    }
}
