package gamemanager;

public enum ArenaStates
{
  ENABLED,  DISABLED,  WAITING,  STARTING,  INGAME,  ENDING,  RESTARTING;
  
  public ArenaStates states;
  
  public ArenaStates getState(){
	  return this.states;
  }
  
  public ArenaStates setState(ArenaStates states){
	  return this.states = states;
  }
  
  public boolean isState(ArenaStates states){
	  return this.states == states;
  }
}
