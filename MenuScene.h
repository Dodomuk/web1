
#ifndef __Menu_SCENE_H__
#define __Menu_SCENE_H__

#include "cocos2d.h"

USING_NS_CC;

class MenuScene : public cocos2d::Scene
{
public:
    
    static cocos2d::Scene* createScene();
    virtual bool init();
    CREATE_FUNC(MenuScene);

    void changeScene(Ref* sender);
    void menuCallback(Ref* pSender);
};

#endif // __MenuScene_SCENE_H__
