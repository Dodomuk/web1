

#include "MenuScene.h"
#include "GameScene.h"

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

    auto bgLayer = Layer::create();
    this->addChild(bgLayer);

    auto layer_1 = Sprite::create("startScene_2.png"); //450*720
    layer_1->setAnchorPoint(Point::ZERO);
    layer_1->setPosition(Point::ZERO);
    bgLayer->addChild(layer_1);

    auto label_start = Label::createWithSystemFont("START", "Gill Sans Ultra Bold Condensed", 60);
    label_start->setColor(Color3B::WHITE);
    auto label_quit = Label::createWithSystemFont("QUIT", "Gill Sans Ultra Bold Condensed", 60);
    label_quit->setColor(Color3B::WHITE);

    auto item_1 = MenuItemLabel::create(label_start, CC_CALLBACK_1(MenuScene::changeScene, this));
    auto item_2 = MenuItemLabel::create(label_quit, CC_CALLBACK_1(MenuScene::menuCallback, this));

    auto menu = Menu::create(item_1, item_2, nullptr);
    menu->alignItemsVertically();
    menu->setPosition(Vec2(100, 610));
    this->addChild(menu);

    return true;
}
void MenuScene::changeScene(Ref* sender)
{
    auto scene = TransitionSplitRows::create(1.5, GameScene::createScene());
    Director::getInstance()->replaceScene(scene);
}
void MenuScene::menuCallback(Ref* pSender)
{
    Director::getInstance()->end();
}
