import java.awt.*;
import java.net.PortUnreachableException;
import java.util.ArrayList;


public class GameController {
    
    private GameBackground gameBackground;
    private GameBlock gameBlock;
    private GamePanel gamePanel;

    public GameController(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    /**
     * 게임화면,게임블록 초기화
     */
    public void initGame(){
        gameBackground = new GameBackground();
        gameBlock = new GameBlock();
        this.gamePanel.setGameBackground(gameBackground);
        this.gamePanel.setGameBlock(gameBlock);

    }


    /**
     * 게임시작
     * 게임오브젝트초기화,게임화면그리기,타이머작동
     */
    public void startGame(){
        initGame();
        repaintGame();
        new GameTimer().run(this);


    }

    /**
     * 게임판화면 그리기
     */
    public void repaintGame(){
        this.gamePanel.repaint();
    }

    /**
     * 게임판 블록좌우이동 요청
     * @param direction 이동방향
     */
    public void requestMoveBlockHorizontal(GameBlock.Direction direction){
        ArrayList<Point> points = this.gameBlock.getMovablePosition(direction);
        boolean isEnable = this.gameBackground.isMovable(points);
        if(isEnable) {
            this.gameBlock.moveToBlock(points);
            repaintGame();
        }
    }

    /**
     * 게임판 블록하단이동 요청
     * @param direction 이동방향
     */
    public void requestMoveBlockDown(GameBlock.Direction direction){
        ArrayList<Point> movablePoints = this.gameBlock.getMovablePosition(direction);
        if(this.gameBackground.isAddible(movablePoints)){
            this.gameBlock.moveToBlock(movablePoints);
        }
        else{
            ArrayList<Point> currentPoints = this.gameBlock.getCurrentBlockPosition();

            Color color = this.gameBlock.getCurrentBlockColor();

            this.gameBackground.addBlock(currentPoints,color);

        }
        repaintGame();

    }




    
    
}
