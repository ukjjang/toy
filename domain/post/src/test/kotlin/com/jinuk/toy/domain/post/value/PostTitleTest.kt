package com.jinuk.toy.domain.post.value

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec

class PostTitleTest : DescribeSpec(
    {
        describe("PostTitle create") {
            it("success - validate") {
                PostTitle("x")
                PostTitle((1..50).joinToString("") { "x" })
            }

            it("fail - validate") {
                shouldThrow<IllegalArgumentException> {
                    PostTitle("")
                }

                shouldThrow<IllegalArgumentException> {
                    PostTitle((1..51).joinToString("") { "x" })
                }
            }
        }
    },
)
