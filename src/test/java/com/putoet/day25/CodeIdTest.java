package com.putoet.day25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeIdTest {
    @Test
    void rowStart() {
        assertEquals(1, CodeId.rowStart(1));
        assertEquals(2, CodeId.rowStart(2));
        assertEquals(4, CodeId.rowStart(3));
        assertEquals(7, CodeId.rowStart(4));
        assertEquals(11, CodeId.rowStart(5));
        assertEquals(16, CodeId.rowStart(6));
    }

    @Test
    void cell() {
        assertEquals(1, CodeId.cell(1,1));
        assertEquals(3, CodeId.cell(1,2));
        assertEquals(6, CodeId.cell(1,3));
        assertEquals(10, CodeId.cell(1,4));
        assertEquals(15, CodeId.cell(1,5));
        assertEquals(21, CodeId.cell(1,6));
        assertEquals(2, CodeId.cell(2,1));
        assertEquals(5, CodeId.cell(2,2));
        assertEquals(9, CodeId.cell(2,3));
        assertEquals(14, CodeId.cell(2,4));
        assertEquals(20, CodeId.cell(2,5));
        assertEquals(4, CodeId.cell(3,1));
        assertEquals(8, CodeId.cell(3,2));
        assertEquals(13, CodeId.cell(3,3));
        assertEquals(19, CodeId.cell(3,4));
        assertEquals(7, CodeId.cell(4,1));
        assertEquals(12, CodeId.cell(4,2));
        assertEquals(18, CodeId.cell(4,3));
        assertEquals(11, CodeId.cell(5,1));
        assertEquals(17, CodeId.cell(5,2));
        assertEquals(16, CodeId.cell(6,1));
    }

    @Test
    void valueAtCell() {
        final CodeId codeId = new CodeId();

        assertEquals(20151125, codeId.valueAtCell(1,1));
        assertEquals(18749137, codeId.valueAtCell(1,2));
        assertEquals(33511524, codeId.valueAtCell(1,6));
        assertEquals(31916031, codeId.valueAtCell(2,1));
        assertEquals(33071741, codeId.valueAtCell(6,1));
    }


}