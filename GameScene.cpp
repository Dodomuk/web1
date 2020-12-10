

#include "GameScene.h"
#include "MenuScene.h"
#include "SimpleAudioEngine.h"

USING_NS_CC;
using namespace CocosDenshion;

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
    initScore();
    initLife();
    StartMusic();

    this->scheduleUpdate();
    this->schedule(schedule_selector(GameScene::setMissile), 0.2);
    this->schedule(schedule_selector(GameScene::setItem), 5.0 + rand() % 4);
    this->schedule(schedule_selector(GameScene::setUfo), 0.2 * (rand() % 10));

    newScore = 0;

    return true;
}
//======화면이 처음 실행될때 실행되는 리스트 
void GameScene::initData() 
{
    winSize = Director::getInstance()->getWinSize();
    items.clear();
    ufos.clear();
    redufos.clear();
    missiles.clear();

    life = 3;

    isGetItem = false;
    newScore = 0;
    highscore = UserDefault::getInstance()->getIntegerForKey("HIGH_SCORE", 0);
}

    //====================우주배경
void GameScene::initBG()
{
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

//=============================사용자의 목숨
void GameScene::initLife()
{
    auto labelLife = Label::createWithSystemFont("", "", 30);
    labelLife->setAnchorPoint(Vec2(0, 1));
    labelLife->setPosition(Vec2(10, winSize.height - 10));
    labelLife->setColor(Color3B::WHITE);
    labelLife->setTag(TAG_LABEL_LIFE);
    this->addChild(labelLife);
    UserDefault::getInstance()->setIntegerForKey("Life", life);
    UserDefault::getInstance()->flush();
    setLabelLife();
}
void GameScene::setLabelLife()
{
    auto label = (Label*)this->getChildByTag(TAG_LABEL_LIFE);
    label->setString(StringUtils::format("LIFE : %d", life));
}

//=============================점수
void GameScene::initScore()
{
    int highscore = UserDefault::getInstance()->getIntegerForKey("HIGHSCORE", 0);

    auto label = Label::createWithSystemFont(StringUtils::
        format("SCORE : %d/%d",score,highscore),"", 30);

    label->setAnchorPoint(Vec2(1, 1));
    label->setPosition(Vec2(winSize.width - 10, winSize.height - 10));
    label->setTag(TAG_LABEL_HIGHSCORE);
    label->setColor(Color3B::WHITE);
    this->addChild(label);
    sum(0);
}

// ===========================비행기(사용자)
void GameScene::initPlane() 
{

    SpriteFrameCache::getInstance()->addSpriteFramesWithFile("airplane.plist");
    auto plane_1 = Sprite::create("airplane.png");
    plane_1->setPosition(Vec2(winSize.width / 2, winSize.height / 4));
    plane_1->setTag(TAG_SPRITE_PLANE);
    plane_1->setScale(0.5);
    this->addChild(plane_1,1);
    auto animation = Animation::create();
    animation->setDelayPerUnit(0.2);
    for (int i = 1; i < 4; i++) {
        auto frame = SpriteFrameCache::getInstance()->getSpriteFrameByName(StringUtils::format("plane_%d.png", i));
        animation->addSpriteFrame(frame);
    }
    auto animate = Animate::create(animation);
    plane_1->runAction(RepeatForever::create(animate));

}

// ===========================미사일
void GameScene::setMissile(float delta)
{

    auto plane_1 = (Sprite*)this->getChildByTag(TAG_SPRITE_PLANE);
    Sprite* missile_1;

    //아이템을 먹을 경우 강한 불빛으로 변경
    if (isGetItem) {
        missile_1 = Sprite::create("fire.png");
        missile_1->setScale(0.15);

        missile_1->setTag(5);
    }
    else {
        missile_1 = Sprite::create("missile_1.png");
        missile_1->setScale(0.05);
       
        missile_1->setTag(1);

    }

    missile_1->setPosition(plane_1->getPosition() + Vec2(0, 100));
    this->addChild(missile_1);
    missiles.pushBack(missile_1);
    auto action = Sequence::create(MoveBy::create(1.5, Vec2(0, winSize.height-10)),
        CallFuncN::create(CC_CALLBACK_1(GameScene::resetMissile, this)),
        NULL);
    missile_1->runAction(action);

}

// ===========================미사일 제거
void GameScene::resetMissile(Ref* sender) //화면서 사라진 미사일도 지운다
{
    auto missile_1 = (Sprite*)sender;
    missiles.eraseObject(missile_1);
    this->removeChild(missile_1);
}

// ===========================아이템 설정
void GameScene::setItem(float delta)
{
    int x = PADDING_SCREEN + rand() % ((int)winSize.width - PADDING_SCREEN * 2);
    auto item = Sprite::create("item.png");
    item->setScale(0.5);
    item->setPosition(Vec2(x, winSize.height));
    this->addChild(item);

    items.pushBack(item);

    auto action = Sequence::create(
        MoveBy::create(3.0, Vec2(0, -winSize.height)),
        CallFuncN::create(CC_CALLBACK_1(GameScene::resetItem, this)),
        NULL);
    item->runAction(action);

}

//아이템 제거
void GameScene::resetItem(Ref* sender)//화면에서 아이템이 지워질시 아이템 리스트 제거
{
    auto item = (Sprite*)sender;

    items.eraseObject(item);

    this->removeChild(item);
}
// ===========================아이템 설정 off
void GameScene::resetGetItem(float delta)
{
    isGetItem = false;
}

// ===========================UFO
void GameScene::setUfo(float delta)
{

    float x = PADDING_SCREEN + rand() % ((int)winSize.width - PADDING_SCREEN * 2);

    Sprite* ufo_red;
    Sprite* ufo;

    //30의 확률로 빨간 우주선 출현
    if (rand() % 100 < 30) {
        SpriteFrameCache::getInstance()->addSpriteFramesWithFile("ufo_red.plist");
        ufo_red = Sprite::create("ufo_red.png");
        ufo_red->setScale(0.4);
        ufo_red->setTag(5);

        auto animation = Animation::create();
        animation->setDelayPerUnit(0.2);

        for (int i = 1; i < 6; i++) {
            auto frame = SpriteFrameCache::getInstance()->getSpriteFrameByName(StringUtils::format("ufo_red %d.png", i));
            animation->addSpriteFrame(frame);
        }

        auto animate = Animate::create(animation);
        ufo_red->runAction(RepeatForever::create(animate));

        ufo_red->setPosition(Point(x, winSize.height) + Vec2(0, 250));
        this->addChild(ufo_red);
        ufos.pushBack(ufo_red);
        auto action = Sequence::create(
            MoveBy::create(4.0, Vec2(0, -1600 - 200)),
            CallFuncN::create(CC_CALLBACK_1(GameScene::resetUfo, this)),
            NULL);
        ufo_red->runAction(action);
    }
    //나머지 확률로 초록색 우주선 출현
    else {
        SpriteFrameCache::getInstance()->addSpriteFramesWithFile("ufo_green.plist");
        ufo = Sprite::create("ufo_green.png");
        ufo->setScale(0.4);
        ufo->setTag(1);

        auto animation = Animation::create();
        animation->setDelayPerUnit(0.2);

        for (int i = 1; i < 6; i++) {
            auto frame = SpriteFrameCache::getInstance()->getSpriteFrameByName(StringUtils::format("ufo_green %d.png", i));
            animation->addSpriteFrame(frame);
        }

        auto animate = Animate::create(animation);
        ufo->runAction(RepeatForever::create(animate));

        ufo->setPosition(Point(x, winSize.height) + Vec2(0, 250));
        this->addChild(ufo);
        ufos.pushBack(ufo);
        auto action = Sequence::create(
            MoveBy::create(4.0, Vec2(0, -1600 - 200)),
            CallFuncN::create(CC_CALLBACK_1(GameScene::resetUfo, this)),
            NULL);
        ufo->runAction(action);
    }

}
//===========================빨간 UFO 제거
void GameScene::resetRedUfo(Ref* sender)
{
    auto ufo_red = (Sprite*)sender;
    redufos.eraseObject(ufo_red);
    this->removeChild(ufo_red);

}

// ===========================UFO 제거
void GameScene::resetUfo(Ref* sender)
{
    auto ufo = (Sprite*)sender;
    ufos.eraseObject(ufo);
    this->removeChild(ufo);
}

// ===========================파티클 설정
void GameScene::setParticle()
{

    Sprite* plane_1 = (Sprite*)this->getChildByTag(TAG_SPRITE_PLANE);
    SimpleAudioEngine::getInstance()->playEffect("explosion.mp3");

    auto particle = ParticleSystemQuad::create("particle_texture_2.plist");
    particle->setPosition(plane_1->getPosition());
    particle->setScale(0.5);
    auto action = Sequence::create(DelayTime::create(0.5), CallFuncN::
        create(CC_CALLBACK_1(GameScene::resetParticle, this)), NULL);

    this->addChild(particle);

    particle->runAction(action);
}
void GameScene::resetParticle(Ref* sender)
{
    auto particle = (Sprite*)sender;
    this->removeChild(particle);
}

//============================점수 추가
void GameScene::sum(int score)
{
    newScore += score;

    if (newScore >= UserDefault::getInstance()->getIntegerForKey("HIGHSCORE")) {
        newScore = UserDefault::getInstance()->getIntegerForKey("HIGHSCORE");
        UserDefault::getInstance()->setIntegerForKey("HIGHSCORE",
            UserDefault::getInstance()->getIntegerForKey("HIGHSCORE"));
        UserDefault::getInstance()->flush();
    }
    auto label = (Label*)this->getChildByTag(TAG_LABEL_HIGHSCORE);
    label->setString(StringUtils::format("SCORE : %d/%d", newScore,
        UserDefault::getInstance()->getIntegerForKey("HIGHSCORE")));

}

// ===========================충돌 효과
void GameScene::update(float delta)
{
    Sprite* plane_1 = (Sprite*)this->getChildByTag(TAG_SPRITE_PLANE);
    Rect rectPlane = Rect(plane_1->getPositionX() - 10, 
        plane_1->getPositionY() - 10, 15, 15);
    auto removeSpr = Sprite::create();
    
    
    for (Sprite* sprItem : items) {
        Rect rectItem = sprItem->getBoundingBox();
        //아이템과 플레이어가 부딪히면 효과음 + 아이템이 사라지면서 isGetItem true 반환=> 미사일 강화
        if (rectPlane.intersectsRect(rectItem)) {
            SimpleAudioEngine::getInstance()->playEffect("levelup.mp3");
            removeSpr = sprItem;
        }
    }
    if (items.contains(removeSpr)) {
        resetItem(removeSpr);
        isGetItem = true;
        this->scheduleOnce(schedule_selector(GameScene::resetGetItem), 5.0);
    }

    auto removeMissile = Sprite::create();
    auto removeUfo = Sprite::create();
    auto removeRedUfo = Sprite::create();

    for (Sprite* missile_1 : missiles) {
        Rect rectMissile = missile_1->getBoundingBox();
        for (Sprite* ufo : ufos) {
            Rect rectUfo = ufo->getBoundingBox();
     
            if (rectMissile.intersectsRect(rectUfo)) { //미사일과 적이 충돌할 경우
                int attack = missile_1->getTag();
                int hp = ufo->getTag();
                removeMissile = missile_1;

                //피가 0보다 많을시 -1
                if (hp - attack > 0) {
                    ufo->setTag(hp - attack);
                }
                else {
                //피가0이되면 적 제거
                    removeUfo = ufo;
                    SimpleAudioEngine::getInstance()->playEffect("Laser_Shoot.wav");
                    auto particle = ParticleSystemQuad::create("particle_texture.plist");
                    particle->setPosition(ufo->getPosition());
                    particle->setScale(0.5);
                    auto action = Sequence::create(DelayTime::create(0.5), CallFuncN::
                        create(CC_CALLBACK_1(GameScene::resetParticle, this)), NULL);
            
                    this->addChild(particle);

                    particle->runAction(action);
               
                    sum(1);
                }
            }
            if(rectPlane.intersectsRect(rectUfo)) { //비행기와 적이 충돌할 경우

                if (life > 0) {
                    life--;
                    UserDefault::getInstance()->setIntegerForKey("Life", life);
                    UserDefault::getInstance()->flush();
                    auto labelScore = (Label*)this->getChildByTag(TAG_LABEL_LIFE);
                    labelScore->setString(StringUtils::format("Life : %d", life));
                    resetContain();
                    setLabelLife();
                    setParticle();
                }
                if (life == 0){

                    setParticle();
                    GameOver();
                    allStop();
                    resetContain();
                }
              
            }
        }


        for (Sprite* ufo_red : redufos) {
            Rect rectRedUfo = ufo_red->getBoundingBox();
     
            if (rectMissile.intersectsRect(rectRedUfo)) { //미사일과 적이 충돌할 경우
                int attack = missile_1->getTag();
                int hp = ufo_red->getTag();
                removeMissile = missile_1;

                if (hp - attack > 0) {
                    ufo_red->setTag(hp - attack);
                }
                else {
                    SimpleAudioEngine::getInstance()->playEffect("Laser_Shoot.wav");
                    removeRedUfo = ufo_red;
                    auto particle = ParticleSystemQuad::create("particle_texture.plist");
                    particle->setPosition(ufo_red->getPosition());
                    particle->setScale(0.5);
                    auto action = Sequence::create(DelayTime::create(0.5), CallFuncN::
                        create(CC_CALLBACK_1(GameScene::resetParticle, this)), NULL);

                    this->addChild(particle);
                    particle->runAction(action);

                    sum(10);
      
                   
                }
            }
            if (rectPlane.intersectsRect(rectRedUfo)) { //비행기와 적이 충돌할 경우

                if (life > 0) {
                    life--;
                                        UserDefault::getInstance()->setIntegerForKey("Life", life);
                    UserDefault::getInstance()->flush();
                    auto labelScore = (Label*)this->getChildByTag(TAG_LABEL_LIFE);
                    labelScore->setString(StringUtils::format("Life : %d", life));
                    resetContain();
                    setLabelLife();
                    setParticle();
        
                }
                if(life == 0) {
                    
                    setParticle();
                    GameOver();
                    allStop();
                    resetContain();
                }

            }
        }


    }


    if (missiles.contains(removeMissile)) {
        resetMissile(removeMissile);
        resetUfo(removeUfo);
        resetRedUfo(removeRedUfo);
    }
}

// ===========================터치이벤트
bool GameScene::onTouchBegan(Touch* touch, Event* unused_event)
{
    //======================비행기 이동 장치
    location = touch->getLocation();

    auto plane_1 = (Sprite*)this->getChildByTag(TAG_SPRITE_PLANE);
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
 
    auto plane_1 = (Sprite*)this->getChildByTag(TAG_SPRITE_PLANE);
    plane_1->setPosition((locationPlane + moveArea).x, winSize.height / 4);

}

// ===========================게임 오버 라벨
void GameScene::GameOver()
{
    auto label = Label::createWithSystemFont("GAME OVER", "", 50);
    label->setPosition(Vec2(winSize.width / 2, winSize.height / 2));
    label->setColor(Color3B::RED);
    this->addChild(label);
    isTurn = true;


}

// ===========================화면 전환
void GameScene::changeScenes(Ref* sender)
{
    StopMusic();
    auto scene = TransitionPageTurn::create(1.0, MenuScene::createScene(), true);
    Director::getInstance()->replaceScene(scene);
    
}


// ===========================게임이 끝났을때
void GameScene::allStop()
{
    this->unschedule(schedule_selector(GameScene::setMissile));
    this->unschedule(schedule_selector(GameScene::setUfo));
    this->unschedule(schedule_selector(GameScene::setItem));
}
void GameScene::resetContain()
{

    for (Sprite* missile_1 : missiles) {
        removeChild(missile_1);
    }
    for (Sprite* ufo : ufos) {
        removeChild(ufo);
    }
    for (Sprite* ufo_red : redufos) {
        removeChild(ufo_red);
    }
    for (Sprite* item : items) {
        removeChild(item);
    }

}

//배경음악
void GameScene::StartMusic()
{
    SimpleAudioEngine::getInstance()->playBackgroundMusic("LetsMarch.mp3");
}
void GameScene::StopMusic()
{
    SimpleAudioEngine::getInstance()->stopBackgroundMusic();
}