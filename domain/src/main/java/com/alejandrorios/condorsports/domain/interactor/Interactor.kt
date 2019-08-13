package com.alejandrorios.condorsports.domain.interactor

/**
 * Created by Alejandro Rios on 2019-07-22
 */
interface Interactor<Response, Params> where Response : Any {
    suspend operator fun invoke(
        params: Params
    ): Response
}
