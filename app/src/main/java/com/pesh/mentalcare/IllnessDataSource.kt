package com.pesh.mentalcare

import com.pesh.mentalcare.model.IllnessTypes

class IllnessDataSource {

    companion object{
        val illnessListDS = ArrayList<IllnessTypes>()
        fun newInstance(): IllnessDataSource{
            return IllnessDataSource()
        }

        fun createDataSet(): ArrayList<IllnessTypes> {


            illnessListDS.add(
                IllnessTypes(
                    mentalIllness = "Anxiety disorders",
                    explanation = "These mental disorders come with excessive worry, fear and nervousness. Some of them include:" +
                            "agoraphobia, phobias, panic disorder, obsessive-compulsive disorder and social anxiety"
                )
            )
            illnessListDS.add(
                IllnessTypes(
                    mentalIllness = "Anxiety disorders",
                    explanation = "These mental disorders come with excessive worry, fear and nervousness. Some of them include:" +
                            "agoraphobia, ph  obias, panic disorder, obsessive-compulsive disorder and social anxiety"
                )
            )
            illnessListDS.add(
                IllnessTypes(
                    mentalIllness = "Impilse controll disorder",
                    explanation = "These mental disorders come with excessive worry, fear and nervousness. Some of them include:" +
                            "agoraphobia, phobias, panic disorder, obsessive-compulsive disorder and social anxiety"
                )
            )
            illnessListDS.add(
                IllnessTypes(
                    mentalIllness = "Trauma-Related disorders",
                    explanation = "These mental disorders may lead toone causing themselves or others harm they include:" +
                            "kleptomania, psychotic disorder and schizophrenia"
                )
            )
            return illnessListDS;

        }

    }
}