package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTestSuite {

    @Autowired
    private TrelloValidator trelloValidator;
    @Test
    public void testValidateCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test", "test", "1", "1");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        Assert.assertEquals("test", trelloCard.getName());
    }

    @Test
    public void testValidateCardWithoutTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("XYZ", "XYZ", "1", "1");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        Assert.assertEquals("XYZ", trelloCard.getName());
    }
}
