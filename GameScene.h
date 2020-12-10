
#ifndef __Game_SCENE_H__
#define __Game_SCENE_H__

#include "cocos2d.h"

USING_NS_CC;

#define TAG_SPRITE_PLANE            1000
#define PADDING_SCREEN              10

#define TAG_LABEL_LIFE              99
#define TAG_LABEL_HIGHSCORE         100

class GameScene : public cocos2d::Scene
{
public:
    
    static cocos2d::Scene* createScene();

    virtual bool init();
    //void menuCloseCallback(cocos2d::Ref* pSender);
    CREATE_FUNC(GameScene);

    Size winSize;

    Vec2 location,locationPlane;
    
    Vector<Sprite*> ufos, redufos, items, missiles;

    bool isGetItem;
    bool isTurn;

    void update(float delta);
    void initData();
    void initBG();
    void initPlane();
    void initScore();
    void initLife();

 
    void setLabelLife();
    void setParticle();

    int newScore,score, highscore, life;
    void sum(int score);
    void resetParticle(Ref* sender);
    void resetGetItem(float delta);

    void setMissile(float delta);
    void resetMissile(Ref* sender);
    
    void setItem(float delta);
    void resetItem(Ref* sender);

    void setUfo(float delta);
    void resetUfo(Ref* sender);
    void resetRedUfo(Ref* sender);

    void GameOver();
    void allStop();
    void changeScenes(Ref* sender);
    void resetContain();

    bool onTouchBegan(Touch* touch, Event* unused_event);
    void onTouchMoved(Touch* touch, Event* unused_event);

    void StartMusic();
    void StopMusic();
};

#endif // __GameScene_SCENE_H__
