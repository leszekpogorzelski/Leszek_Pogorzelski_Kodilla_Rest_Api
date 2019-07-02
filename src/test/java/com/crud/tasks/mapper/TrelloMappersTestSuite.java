package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.client.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMappersTestSuite {


@Autowired
    private TrelloMapper trelloMapper;

@Test
    public void testMapToCardDto() {
    //Given
    TrelloCard trelloCard = new TrelloCard("XYZ", "XYZ", "XYZ", "XYZ");
    //When
    TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
    //Then
    Assert.assertEquals("XYZ", trelloCardDto.getName());

}

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("XYZ", "XYZ", "XYZ", "XYZ");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals("XYZ", trelloCard.getName());

    }

    @Test
    public void mapToBoardsDto() {
    //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();

        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloList> trelloLists1 = new ArrayList<>();

        trelloLists.add(new TrelloList("List1", "List1", false));
        trelloLists.add(new TrelloList("List2", "List2", false));

        trelloLists1.add(new TrelloList("List3", "List4", false));
        trelloLists1.add(new TrelloList("List3", "List4", false));

        trelloBoards.add(new TrelloBoard("XYZ", "XYZ", trelloLists ));
        trelloBoards.add(new TrelloBoard("ZYX", "ZYX", trelloLists1 ));
        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        Assert.assertEquals(2, trelloBoardDto.size());

    }

    @Test
    public void mapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();

        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        List<TrelloListDto> trelloListsDto1 = new ArrayList<>();

        trelloListsDto.add(new TrelloListDto("List1", "List1", false));
        trelloListsDto.add(new TrelloListDto("List2", "List2", false));

        trelloListsDto1.add(new TrelloListDto("List3", "List4", false));
        trelloListsDto1.add(new TrelloListDto("List3", "List4", false));

        trelloBoardsDto.add(new TrelloBoardDto("XYZ", "XYZ", trelloListsDto ));
        trelloBoardsDto.add(new TrelloBoardDto("ZYX", "ZYX", trelloListsDto1 ));
        //When
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        Assert.assertEquals(2, trelloBoard.size());
    }

    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();

        trelloListsDto.add(new TrelloListDto("XYZ", "XYZ", false));
        trelloListsDto.add(new TrelloListDto("XYZ1", "XYZ1", false));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        //Then
        Assert.assertEquals(2, trelloLists.size());
    }
    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();

        trelloLists.add(new TrelloList("XYZ", "XYZ", false));
        trelloLists.add(new TrelloList("XYZ1", "XYZ1", false));

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals(2, trelloListsDto.size());
    }
}
