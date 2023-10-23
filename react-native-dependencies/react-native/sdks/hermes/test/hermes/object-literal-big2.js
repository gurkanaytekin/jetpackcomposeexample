/**
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

// RUN: %hermes -target=HBC -O %s | %FileCheck --match-full-lines %s
// RUN: %hermes -target=HBC -O -emit-binary -out %t.hbc %s && %hermes %t.hbc | %FileCheck --match-full-lines %s
// Test object literals hidden class caching code for objects with more than 255 properties -- it should not crash/assert.

// Create a big object with more than 255 properties.
var obj = {
  p1: 'val1',
  p2: 'val2',
  p3: 'val3',
  p4: 'val4',
  p5: 'val5',
  p6: 'val6',
  p7: 'val7',
  p8: 'val8',
  p9: 'val9',
  p10: 'val10',
  p11: 'val11',
  p12: 'val12',
  p13: 'val13',
  p14: 'val14',
  p15: 'val15',
  p16: 'val16',
  p17: 'val17',
  p18: 'val18',
  p19: 'val19',
  p20: 'val20',
  p21: 'val21',
  p22: 'val22',
  p23: 'val23',
  p24: 'val24',
  p25: 'val25',
  p26: 'val26',
  p27: 'val27',
  p28: 'val28',
  p29: 'val29',
  p30: 'val30',
  p31: 'val31',
  p32: 'val32',
  p33: 'val33',
  p34: 'val34',
  p35: 'val35',
  p36: 'val36',
  p37: 'val37',
  p38: 'val38',
  p39: 'val39',
  p40: 'val40',
  p41: 'val41',
  p42: 'val42',
  p43: 'val43',
  p44: 'val44',
  p45: 'val45',
  p46: 'val46',
  p47: 'val47',
  p48: 'val48',
  p49: 'val49',
  p50: 'val50',
  p51: 'val51',
  p52: 'val52',
  p53: 'val53',
  p54: 'val54',
  p55: 'val55',
  p56: 'val56',
  p57: 'val57',
  p58: 'val58',
  p59: 'val59',
  p60: 'val60',
  p61: 'val61',
  p62: 'val62',
  p63: 'val63',
  p64: 'val64',
  p65: 'val65',
  p66: 'val66',
  p67: 'val67',
  p68: 'val68',
  p69: 'val69',
  q1: 'val1',
  q2: 'val2',
  q3: 'val3',
  q4: 'val4',
  q5: 'val5',
  q6: 'val6',
  q7: 'val7',
  q8: 'val8',
  q9: 'val9',
  q10: 'val10',
  q11: 'val11',
  q12: 'val12',
  q13: 'val13',
  q14: 'val14',
  q15: 'val15',
  q16: 'val16',
  q17: 'val17',
  q18: 'val18',
  q19: 'val19',
  q20: 'val20',
  q21: 'val21',
  q22: 'val22',
  q23: 'val23',
  q24: 'val24',
  q25: 'val25',
  q26: 'val26',
  q27: 'val27',
  q28: 'val28',
  q29: 'val29',
  q30: 'val30',
  q31: 'val31',
  q32: 'val32',
  q33: 'val33',
  q34: 'val34',
  q35: 'val35',
  q36: 'val36',
  q37: 'val37',
  q38: 'val38',
  q39: 'val39',
  q40: 'val40',
  q41: 'val41',
  q42: 'val42',
  q43: 'val43',
  q44: 'val44',
  q45: 'val45',
  q46: 'val46',
  q47: 'val47',
  q48: 'val48',
  q49: 'val49',
  q50: 'val50',
  q51: 'val51',
  q52: 'val52',
  q53: 'val53',
  q54: 'val54',
  q55: 'val55',
  q56: 'val56',
  q57: 'val57',
  q58: 'val58',
  q59: 'val59',
  q60: 'val60',
  q61: 'val61',
  q62: 'val62',
  q63: 'val63',
  q64: 'val64',
  q65: 'val65',
  q66: 'val66',
  q67: 'val67',
  q68: 'val68',
  q69: 'val69',
  r1: 'val1',
  r2: 'val2',
  r3: 'val3',
  r4: 'val4',
  r5: 'val5',
  r6: 'val6',
  r7: 'val7',
  r8: 'val8',
  r9: 'val9',
  r10: 'val10',
  r11: 'val11',
  r12: 'val12',
  r13: 'val13',
  r14: 'val14',
  r15: 'val15',
  r16: 'val16',
  r17: 'val17',
  r18: 'val18',
  r19: 'val19',
  r20: 'val20',
  r21: 'val21',
  r22: 'val22',
  r23: 'val23',
  r24: 'val24',
  r25: 'val25',
  r26: 'val26',
  r27: 'val27',
  r28: 'val28',
  r29: 'val29',
  r30: 'val30',
  r31: 'val31',
  r32: 'val32',
  r33: 'val33',
  r34: 'val34',
  r35: 'val35',
  r36: 'val36',
  r37: 'val37',
  r38: 'val38',
  r39: 'val39',
  r40: 'val40',
  r41: 'val41',
  r42: 'val42',
  r43: 'val43',
  r44: 'val44',
  r45: 'val45',
  r46: 'val46',
  r47: 'val47',
  r48: 'val48',
  r49: 'val49',
  r50: 'val50',
  r51: 'val51',
  r52: 'val52',
  r53: 'val53',
  r54: 'val54',
  r55: 'val55',
  r56: 'val56',
  r57: 'val57',
  r58: 'val58',
  r59: 'val59',
  r60: 'val60',
  r61: 'val61',
  r62: 'val62',
  r63: 'val63',
  r64: 'val64',
  r65: 'val65',
  r66: 'val66',
  r67: 'val67',
  r68: 'val68',
  r69: 'val69',
  s1: 'val1',
  s2: 'val2',
  s3: 'val3',
  s4: 'val4',
  s5: 'val5',
  s6: 'val6',
  s7: 'val7',
  s8: 'val8',
  s9: 'val9',
  s10: 'val10',
  s11: 'val11',
  s12: 'val12',
  s13: 'val13',
  s14: 'val14',
  s15: 'val15',
  s16: 'val16',
  s17: 'val17',
  s18: 'val18',
  s19: 'val19',
  s20: 'val20',
  s21: 'val21',
  s22: 'val22',
  s23: 'val23',
  s24: 'val24',
  s25: 'val25',
  s26: 'val26',
  s27: 'val27',
  s28: 'val28',
  s29: 'val29',
  s30: 'val30',
  s31: 'val31',
  s32: 'val32',
  s33: 'val33',
  s34: 'val34',
  s35: 'val35',
  s36: 'val36',
  s37: 'val37',
  s38: 'val38',
  s39: 'val39',
  s40: 'val40',
  s41: 'val41',
  s42: 'val42',
  s43: 'val43',
  s44: 'val44',
  s45: 'val45',
  s46: 'val46',
  s47: 'val47',
  s48: 'val48',
  s49: 'val49',
  s50: 'val50',
  s51: 'val51',
  s52: 'val52',
  s53: 'val53',
  s54: 'val54',
  s55: 'val55',
  s56: 'val56',
  s57: 'val57',
  s58: 'val58',
  s59: 'val59',
  s60: 'val60',
  s61: 'val61',
  s62: 'val62',
  s63: 'val63',
  s64: 'val64',
  s65: 'val65',
  s66: 'val66',
  s67: 'val67',
  s68: 'val68',
  s69: 'val69',
};

print("start of test");
//CHECK: start of test

print(obj.p69);
//CHECK-NEXT: val69

print(obj.q69);
//CHECK-NEXT: val69

print(obj.r69);
//CHECK-NEXT: val69

print(obj.s69);
//CHECK-NEXT: val69
