

#include "GameScene.h"
#include "MenuScene.h"

USING_NS_CC;

Scene* GameScene::createScene()
{
    return GameScene::create();
}

bool GameScene::init()
{

    if (!Scene::init())
    {
        return false;
    }
   auto listener = EventListenerTouchOneByOne::create();

    listener->onTouchBegan = CC_CALLBACK_2(GameScene::onTouchBegan, this);
    listener->onTouchMoved = CC_CALLBACK_2(GameScene::onTouchMoved, this);
    Director::getInstance()->getEventDispatcher()
        ->addEventListenerWithFixedPriority(listener, 1);
 
    initData();
    initBG();
    initPlane();

    this->scheduleUpdate();
    this->schedule(schedule_selector(GameScene::setMissile), 0.2);
    this->schedule(schedule_selector(GameScene::setUfo), 0.5 * (rand() % 10));


    return true;
}
void GameScene::initData() 
{
    winSize = Director::getInstance()->getWinSize();
    ufos.clear();
    missiles.clear();

}
void GameScene::initBG()
{
    //====================우주배경
    auto bgLayer = Layer::create();
    this->addChild(bgLayer);

    auto layer_1 = Sprite::create("darkSky.jpg"); //1067 x 1600
    layer_1->setAnchorPoint(Point::ZERO);
    layer_1->setPosition(Point::ZERO);
    bgLayer->addChild(layer_1);

    auto layer_2 = Sprite::create("darkSky.jpg", Rect(0, 0, 967, 1500));
    layer_2->setAnchorPoint(Vec2::ZERO);
    layer_2->setPosition(Vec2(0, 1600));
    bgLayer->addChild(layer_2);

    auto action_0 = MoveBy::create(20.0, Point(0, -1600));
    auto action_1 = Place::create(Point::ZERO);
    auto action_2 = Sequence::create(action_0, action_1, NULL);
    auto action_3 = RepeatForever::create(action_2);
    bgLayer->runAction(action_3);
}
void GameScene::initPlane() 
{
    // ===========================비행기


    auto plane_1 = Sprite::create("airplane.png");
    plane_1->setPosition(Vec2(winSize.width / 2, winSize.height / 4));
    plane_1->setScale(0.06);
    plane_1->setTag(1);
    this->addChild(plane_1,1);

}
void GameScene::setMissile(float delta)
{

    auto plane_1 = (Sprite*)this->getChildByTag(1);
    
    auto missile_1 = Sprite::create("missile_1.png");
    missile_1->setTag(2);
    missile_1->setScale(0.05);
    missile_1->setPosition(plane_1->getPosition() + Vec2(0, 100));
    this ->addChild(missile_1);
    missiles.pushBack(missile_1);
    auto action = Sequence::create(MoveBy::create(1.5, Vec2(0, 1500)),
        CallFuncN::create(CC_CALLBACK_1(GameScene::resetMissile, this)),
        NULL);
    missile_1->runAction(action);

        
}
void GameScene::resetMissile(Ref* sender) //화면서 사라진 미사일도 지운다
{
    auto missile_1 = (Sprite*)sender;
    missiles.eraseObject(missile_1);
    this->removeChild(missile_1);
}
void GameScene::setUfo(float delta)
{

    float x = rand() % ((int)winSize.width - 70);
    float y = winSize.height;

    auto ufo = Sprite::create("ufo.png"); // 1000*879 /2 = 500*439.5
    ufo->setPosition(Point(x, y));
    ufo->setScale(0.1);
    ufo->setTag(3);
    this->addChild(ufo);

    ufos.pushBack(ufo);

    auto action = Sequence::create(
        MoveBy::create(2.5, Vec2(0, -1600 - 200)),
        CallFuncN::create(CC_CALLBACK_1(GameScene::resetUfo, this)),
        NULL);
    ufo->runAction(action);
}
void GameScene::resetUfo(Ref* sender)
{
    auto ufo = (Sprite*)sender;
    ufos.eraseObject(ufo);
    this->removeChild(ufo);
}
void GameScene::update(float delta)
{
    Sprite* plane_1 = (Sprite*)this->getChildByTag(TAG_SPRITE_PLANE);
    Rect rectPlane = plane_1->getBoundingBox();
    auto removePlane = Sprite::create();
    auto removeMissile = Sprite::create();
    auto removeUfo = Sprite::create();

    for (Sprite* missile_1 : missiles) {
        Rect rectMissile = missile_1->getBoundingBox();
        for (Sprite* ufo : ufos) {
            Rect rectUfo = ufo->getBoundingBox();
            /*  Rect rectPlane = Rect(sprPlane->getPositionX(), sprPlane->getPositionY(),
          sprPlane->getContentSize().width, sprPlane->getContentSize().height);*/
            if (rectMissile.intersectsRect(rectUfo)) { //미사일과 적이 충돌할 경우
                removeMissile = missile_1;
                removeUfo = ufo;
            }
            else if (rectPlane.intersectsRect(rectUfo)) { //비행기와 적이 충돌할 경우

                GameOver();
                allStop();

            }
        }
    }
    if (missiles.contains(removeMissile)) {
        resetMissile(removeMissile);
        resetUfo(removeUfo);
    }
}
bool GameScene::onTouchBegan(Touch* touch, Event* unused_event)
{
    //======================비행기 이동 장치
    location = touch->getLocation();

    auto plane_1 = (Sprite*)this->getChildByTag(1);
    locationPlane = plane_1->getPosition();

    if (isTurn) {
        auto scene = TransitionPageTurn::create(1.0, MenuScene::createScene(), true);
        Director::getInstance()->replaceScene(scene);
        isTurn = false;
    }

    return true;
}
void GameScene::onTouchMoved(Touch* touch, Event* event)
{
    Vec2 locationTouch = touch->getLocation();
    Vec2 moveArea = locationTouch - location;
 
    auto plane_1 = (Sprite*)this->getChildByTag(1);
    plane_1->setPosition((locationPlane + moveArea).x, winSize.height / 4);

}
void GameScene::GameOver()
{
    auto label = Label::createWithSystemFont("GAME OVER", "", 50);
    label->setPosition(Vec2(winSize.width / 2, winSize.height / 2));
    label->setColor(Color3B::RED);
    this->addChild(label);
    isTurn = true;


}
void GameScene::changeScenes(Ref* sender)
{
    auto scene = TransitionPageTurn::create(1.0, MenuScene::createScene(), true);
    Director::getInstance()->replaceScene(scene);
}
void GameScene::allStop()
{
    this->unschedule(schedule_selector(GameScene::setMissile));
    this->unschedule(schedule_selector(GameScene::setUfo));
}
