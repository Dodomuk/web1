
#ifndef __Game_SCENE_H__
#define __Game_SCENE_H__

#include "cocos2d.h"

USING_NS_CC;

#define TAG_SPRITE_PLANE            1
#define TAG_SPRITE_MISSILE          2  
#define TAG_LABEL_GAMEOVER          10

class GameScene : public cocos2d::Scene
{

public:

    
    static cocos2d::Scene* createScene();

    virtual bool init();
    //void menuCloseCallback(cocos2d::Ref* pSender);
    CREATE_FUNC(GameScene);

    Size winSize;

    Vec2 location;
    Vec2 locationPlane;
    
    Vector<Sprite*> ufos, missiles;

    void update(float delta);
    void initData();
    void initBG();
    void initPlane();
  
    void setMissile(float delta);
    void resetMissile(Ref* sender);
    
    void setUfo(float delta);
    void resetUfo(Ref* sender);
  
    void actionGameEnd(bool isGameOver);
    void endGameEnd();
    void initGameOver();
    //bool isStop;

    bool onTouchBegan(Touch* touch, Event* unused_event);
    void onTouchMoved(Touch* touch, Event* unused_event);


};

#endif // __GameScene_SCENE_H__
