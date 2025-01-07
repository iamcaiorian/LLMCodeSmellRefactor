package org.example.studycards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitnerSystem extends StudyMethod {
    List<Box> boxes = null;

    public LeitnerSystem(String methodName) {
        super(methodName);
        boxes = new ArrayList<>(Arrays.asList(new Box(), new Box(), new Box(), new Box(), new Box()));
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        int index = 0;
        for (Box box : boxes) {
            response.append("Box ").append(index).append(": ").append(box.toString()).append("\n");
            index++;
        }
        return response.toString();
    }

    public void clearBoxes() {
        boxes.clear();
        boxes = new ArrayList<>(Arrays.asList(new Box(), new Box(), new Box(), new Box(), new Box()));
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public String getRandomCard(List<Box> otherBoxes) {
        if (!isValidBoxList(otherBoxes)) {
            return null;
        }

        Box allBoxes = combineBoxes(otherBoxes);
        Integer randomCardId = allBoxes.getRandomCard();

        if (randomCardId == null) {
            return "No card found";
        }

        return formatCardResponse(randomCardId);
    }

    private boolean isValidBoxList(List<Box> boxes) {
        return boxes != null && !boxes.isEmpty();
    }

    private Box combineBoxes(List<Box> boxes) {
        Box allBoxes = new Box();
        for (Box box : boxes) {
            allBoxes.addCards(box.getCards());
        }
        return allBoxes;
    }

    private String formatCardResponse(Integer cardId) {
        CardManager manager = CardManager.getCardManager();
        Card card = manager.getCard(cardId);
        return String.format("[%d] The random question was: %s | The answer is: %s",
                cardId, card.getQuestion(), card.getAnswer());
    }

    public void addCardToBox(Integer id, Integer boxId) {
        this.boxes.get(boxId).addCard(id);
    }

    public void removeCardFromBox(Integer id, Integer boxId) {
        this.boxes.get(boxId).removeCard(id);
    }

    public Card takeCardFromBox(Integer boxId) {
        Integer cardId = boxes.get(boxId).getRandomCard();
        return this.cardManager.getCard(cardId);
    }

    public void boxIdValidation(Integer boxId) throws Exception {
        if (boxId == null || boxId > (boxes.size() - 1) || boxId <= 0) {
            throw new Exception("Invalid box ID");
        }
    }

    public void upgradeCard(Integer cardId, Integer boxId) throws Exception {
        boxIdValidation(boxId);

        Box refBox = boxes.get(boxId);
        if (refBox.hasCard(cardId)) {
            throw new Exception("No card Found");
        }
        refBox.removeCard(cardId);
        boxes.get(Math.min(boxId + 1, 4)).addCard(cardId);
    }

    public void downgradeCard(Integer cardId, Integer boxId) throws Exception {
        boxIdValidation(boxId);
        Box refBox = boxes.get(boxId);
        if (refBox.hasCard(cardId)) {
            throw new Exception("No card Found");
        }
        refBox.removeCard(cardId);
        boxes.get(Math.max(boxId - 1, 0)).addCard(cardId);
    }
}