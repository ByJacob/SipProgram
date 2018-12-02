package pl.edu.pwr.weka.sipprogram.gui.view.animation

import pl.edu.pwr.weka.sipprogram.util.MessageBundle

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 02.12.2018 19:44
 */

val resources = MessageBundle("Messages")

enum class AnimationCaseEnum(val animName: String, val parameters: List<AnimationCaseEnumParameters>) {

    BASIC_REGISTER(resources["name1"], listOf<AnimationCaseEnumParameters>(
            AnimationCaseEnumParameters(resources["title10"], resources["description10"],
                    "", false),
            AnimationCaseEnumParameters(resources["title11"], resources["description11"],
                    resources["register"], true),
            AnimationCaseEnumParameters(resources["title12"], resources["description12"],
                    resources["ack401"], false),
            AnimationCaseEnumParameters(resources["title13"], resources["description13"],
                    resources["register"], true),
            AnimationCaseEnumParameters(resources["title14"], resources["description14"],
                    resources["ack200"], false)
    )),
    UPDATE_CONTACT_REGISTER(resources["name2"], listOf<AnimationCaseEnumParameters>(
    AnimationCaseEnumParameters(resources["title20"], resources["description20"],
    "", false),
    AnimationCaseEnumParameters(resources["title21"], resources["description21"],
    resources["register"], true),
    AnimationCaseEnumParameters(resources["title22"], resources["description22"],
    resources["ack200"], false)
    ));


    override fun toString(): String {
        return animName
    }

    data class AnimationCaseEnumParameters(val title: String, val description: String,
                                           val arrowName: String, val isArrowRight: Boolean)
}