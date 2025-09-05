package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Expense expense) {
        expenseService.saveExpense(expense);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        if (expense.isPresent()) {
            model.addAttribute("expense", expense.get());
            return "edit";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/";
    }
}