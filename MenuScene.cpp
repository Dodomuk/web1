
#include "MenuScene.h"
//#include "audio/include/SimpleAudioEngine.h"

USING_NS_CC;

Scene* MenuScene::createScene()
{
    return MenuScene::create();
}

bool MenuScene::init()
{
    if (!Scene::init())
    {
        return false;
    }
    
    //파티클 효과를 위한 리스너
    auto listenerB = EventListenerTouchOneByOne::create();
    listenerB->onTouchBegan = CC_CALLBACK_2(MenuScene::onTouchBegan, this);
    Director::getInstance()->getEventDispatcher()
        ->addEventListenerWithSceneGraphPriority(listenerB, this);

    /*auto audio = CocosDenshion::SimpleAudioEngine::getInstance();
    audio->preloadEffect("music/explosion.wav");*/

    initAll();
    initData();
    initParticle();
    initBG();
    AnimationScene();
    initGameOver();
    initHighScore();

    this->schedule(schedule_selector(MenuScene::setBird), 1.0);
    this->scheduleUpdate();
    
  
    return true;
}
void MenuScene::initAll()
{
    winSize = Director::getInstance()->getWinSize();
    
    auto listener = EventListenerTouchOneByOne::create();
    listener->onTouchBegan = CC_CALLBACK_2(MenuScene::onTouchBegan, this);
    Director::getInstance()->getEventDispatcher()
        ->addEventListenerWithFixedPriority(listener, 1);
    
    srand(time(NULL));
    
    birds.clear();
}
void MenuScene::initData()
{
    isJump = false; //점프 중복 방지
}
void MenuScene::initParticle()
{
    isParticle = false; //파티클 중복 방지
}
void MenuScene::initBG()
{

    //1920x1080
    auto bgLayer_1 = Layer::create();
    this->addChild(bgLayer_1);

    //=====================상단 화면(배경)
    auto spr_1_0 = Sprite::create("bg_1.png");
    spr_1_0->setAnchorPoint(Point::ZERO);
    spr_1_0->setPosition(Point::ZERO);
    bgLayer_1->addChild(spr_1_0);

    auto spr_1_1 = Sprite::create("bg_1.png", Rect(0, 0, 1870, 1080));
    spr_1_1->setAnchorPoint(Point::ZERO);
    spr_1_1->setPosition(Point(1920, 0));
    bgLayer_1->addChild(spr_1_1);

    auto action_0 = MoveBy::create(20.0, Point(-1920, 0));
    auto action_1 = Place::create(Point::ZERO);
    auto action_2 = Sequence::create(action_0, action_1, NULL);
    auto action_3 = RepeatForever::create(action_2);
    bgLayer_1->runAction(action_3);


    //=====================나무
    auto bgLayer_t = Layer::create();
    this->addChild(bgLayer_t);

    auto spr_tree = Sprite::create("bg_4.png");
    spr_tree->setAnchorPoint(Point::ZERO);
    spr_tree->setPosition(Point::ZERO);
    bgLayer_t->addChild(spr_tree);

    auto spr_tree_1 = Sprite::create("bg_4.png", Rect(0, 0, 1870, 1080));
    spr_tree_1->setAnchorPoint(Point::ZERO);
    spr_tree_1->setPosition(Point(1920, 0));
    bgLayer_t->addChild(spr_tree_1);

    auto action_20 = MoveBy::create(10.5, Point(-1920, 0));
    auto action_21 = Place::create(Point::ZERO);
    auto action_22 = Sequence::create(action_20, action_21, NULL);
    auto action_23 = RepeatForever::create(action_22);
    bgLayer_t->runAction(action_23);


    //=======================하단 화면(타일)
    auto bgLayer_2 = Layer::create();
    this->addChild(bgLayer_2);

    for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 1; j++) {
            auto spr_2_0 = Sprite::create("bg_tile.png");
            spr_2_0->setAnchorPoint(Point::ZERO);
            spr_2_0->setPosition(Point(i * 370, j));
            bgLayer_2->addChild(spr_2_0);

        }
    }
    auto action_10 = MoveBy::create(2.0, Point(-370, 0));
    auto action_11 = Place::create(Point::ZERO);
    auto action_12 = Sequence::create(action_10, action_11, NULL);
    auto action_13 = RepeatForever::create(action_12);
    bgLayer_2->runAction(action_13);
}

//=======================고양이
void MenuScene::AnimationScene()
{
    //고양이 plist
    SpriteFrameCache::getInstance()
        ->addSpriteFramesWithFile("pics/cat_Run.plist");
    //고양이
    auto cat = Sprite::createWithSpriteFrameName("Run (1).png");
    cat->setPosition(Vec2(400, 375));
    cat->setScale(1.0);
    cat->setTag(TAG_SPRITE_CAT);
    this->addChild(cat);

    //고양이 애니메이션화
    auto animation = Animation::create();
    animation->setDelayPerUnit(0.05);

    for (int i = 0; i < 8; i++) {
        auto frame = SpriteFrameCache::getInstance() ->
            getSpriteFrameByName(StringUtils::format("Run (%d).png", i + 1));
        animation->addSpriteFrame(frame);
    }
    //고양이 액션
    auto animate = Animate::create(animation);
    auto actionC = Spawn::create(animate,nullptr);
    cat->runAction(RepeatForever::create(actionC));

}
void MenuScene::resetJump()
{
    isJump = false;
}
//파티클을 재시작
void MenuScene::resetParticle(Ref* sender)
{
    isParticle = false;
    auto particle = (ParticleSystemQuad*)sender;
    this->removeChild(particle);

}
bool MenuScene::onTouchBegan(Touch* touch, Event* unused_event)
{
    Point location = touch->getLocation();
    auto removeSpr = Sprite::create();

    Sprite* spr = (Sprite*)this->getChildByTag(TAG_SPRITE_CAT);

    //내가 만든 파티클
    auto particle = ParticleSystemQuad::create("pics/particle_1.plist");
    particle->setPosition(touch->getLocation());
    particle->setScale(0.5);
    this->addChild(particle);

    for (Sprite* spr1 : birds) {
        Rect rect = spr1->getBoundingBox();
        if (rect.containsPoint(location)) {
            removeSpr = spr1;
            this->removeChild(spr1);

        }
    }
    if (birds.contains(removeSpr))
    {
        birds.eraseObject(removeSpr);

        score += 10;

        if (score >= UserDefault::getInstance()->getIntegerForKey("HIGHSCORE")); {
            UserDefault::getInstance()->setIntegerForKey("HIGHSCORE", score);
            UserDefault::getInstance()->flush();
        }
        auto label = (Label*)this->getChildByTag(4);
        label->setString(StringUtils::format("SCORE : %d / %d"
            ,score, UserDefault::getInstance()->getIntegerForKey("HIGHSCORE")));

    }

    //Rect rect2 = spr->getBoundingBox();
    //    isParticle = true;
    //    auto action = Sequence::create(
    //        DelayTime::create(1.0),
    //        CallFuncN::create(CC_CALLBACK_1(MenuScene::resetParticle, this)),
    //        NULL
    //    );
    //    FadeOut* action_fo = FadeOut::create(0.05);
    //    particle->runAction(action);
    //    sprBird->runAction(action_fo);
    if (isStop) {
        Director::getInstance()->resume();
        isStop = false;
    }
    else
    if (!isJump) {
        isJump = true;
      
        auto action = Sequence::create(
            JumpBy::create(1.0, Vec2::ZERO, 200, 1),
            CallFunc::create(CC_CALLBACK_0(MenuScene::resetJump, this)), NULL);
        spr->runAction(action);
    }
    return true;
}
void MenuScene::setBird(float delta)
{

    float x = winSize.width;
    float y = rand() % ((int)winSize.height-500)+500;

    auto bird = Sprite::create("pics/bird.png");// 693 * 550 /2 = 346 * 225
    bird->setAnchorPoint(Vec2(0.5, 0));
    bird->setPosition(Point(x,y));
    bird->setTag(TAG_SPRITE_BIRD);
    bird->setScale(0.5);
    this->addChild(bird);

    birds.pushBack(bird);

    auto action = Sequence::create(
        MoveBy::create(2.5, Vec2(-1920 - 500, 0)),
        RemoveSelf::create(), NULL);
    bird->runAction(action);

}
void MenuScene::update(float delta)
{
    if (this->getChildByTag(TAG_SPRITE_BIRD) != NULL) {
        Sprite* sprCat= (Sprite*)this->getChildByTag(TAG_SPRITE_CAT);
        Sprite* sprBird = (Sprite*)this->getChildByTag(TAG_SPRITE_BIRD);
        Rect rectCat = Rect(sprCat->getPositionX() - 150, sprCat->getPositionY()-250, 10,
            sprCat->getContentSize().height);
        Rect rectBird = sprBird->getBoundingBox();
        if (rectCat.intersectsRect(rectBird)) {
            Director::getInstance()->pause();
            isStop = true;
        }           
    } 
}
void MenuScene::initGameOver()
{
    auto label = Label::createWithSystemFont("GAME OVER", "", 300);
    label->setPosition(Vec2(965, 540));
    label->setTag(3);
    label->setColor(Color3B::RED);
    label->setVisible(false);
    this->addChild(label);
}
void MenuScene::actionGameEnd(bool isGameClear)
{
    auto action = Sequence::create(
        DelayTime::create(0.5),
        Show::create(),
        EaseBounceOut::create(MoveTo::create(1.0, Vec2(960, 540))),
        DelayTime::create(2.0),
        Hide::create(),
        Place::create(Vec2(960, 540)));
    
    Label* label;
    if (isGameClear) {
        label = (Label*)this->getChildByTag(TAG_LABEL_GAMEOVER);
        
    }
    label->runAction(action);
}
void MenuScene::initHighScore()
{
    int highscore = UserDefault::getInstance()->getIntegerForKey("HIGHSCORE", 0);
    auto label = Label::createWithSystemFont(StringUtils::format("SCORE : %d / %d", score,highscore), "", 50);
    label->setAnchorPoint(Point(0, 1));
    label->setPosition(Point(10, winSize.height - 30));
    label->setTag(4);
    this->addChild(label);
}
