package padev.badhabits.application.mvp.model.entity

open class HabitEntity(
        var id: Long,
        var name: String,
        var time: Boolean,
        var money: Boolean,
        var health: Boolean
) {
}