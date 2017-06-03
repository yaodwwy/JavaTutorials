package sia.knights;
  
public class BraveKnight implements Knight {

  private Quest quest;

  public BraveKnight(Quest quest) {
    this.quest = quest;
  }

  public void embarkOnQuest() {
    System.out.println("embarkOnQuest()");
    quest.embark();
  }

}
