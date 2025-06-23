package com.example.passfashion.controller;

import com.example.passfashion.dto.Request.CreditCardRequest;
import com.example.passfashion.dto.Response.CreditCardResponse;
import com.example.passfashion.model.CreditCard;
import com.example.passfashion.model.User;
import com.example.passfashion.repository.CreditCardRepository;
import com.example.passfashion.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/credit_card")
public class CreditCardController {

    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all/{userid}")
    public List<CreditCard> getCardsByUserId(@PathVariable long userid) throws Exception {
        List<CreditCard> list = creditCardRepository.findAllByUserId((userid));
        return list;
    }

    @PutMapping("/update/{id}")
    public boolean updateCardById(@PathVariable long id, @Valid @RequestBody CreditCardRequest request)
            throws Exception {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(Exception::new);
        creditCard.setNumber(request.getNumber());
        creditCard.setOwnerName(request.getOwnerName());
        creditCard.setExpiryDate(request.getExpiryDate());
        // Save và return true nếu không lỗi
        creditCardRepository.save(creditCard);
        return true;
    }

    @PostMapping("/add-card")
    public boolean addCreditCard(@Valid @RequestBody CreditCardRequest request) {
        CreditCard creditCard = new CreditCard();
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        creditCard.setUser(user);
        creditCard.setNumber(request.getNumber());
        creditCard.setOwnerName(request.getOwnerName());
        creditCard.setExpiryDate(request.getExpiryDate());
        creditCard.setCcv(request.getCcv());
        // Save và return true nếu không lỗi
        creditCardRepository.save(creditCard);
        return true;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCard(@PathVariable long id) {
        creditCardRepository.deleteById(id);
        return true;
    }

}
