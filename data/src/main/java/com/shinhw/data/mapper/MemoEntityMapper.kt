package com.shinhw.data.mapper

import com.shinhw.data.local.entity.MemoRoomEntity
import com.shinhw.domain.base.Mapper
import com.shinhw.domain.entity.Memo

class MemoEntityMapper : Mapper<MemoRoomEntity, Memo> {

    override fun mapFrom(from: MemoRoomEntity) = Memo(
        id = from.id,
        name = from.name,
        memo = from.memo,
        date = from.date
    )

    fun entityToRoom(from: Memo) = MemoRoomEntity(
        id = from.id,
        name = from.name,
        memo = from.memo,
        date = from.date
    )
}