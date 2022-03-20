export default function Article(props) {

    return (
        <div className="article">
            <h1 className='article__title'>Article</h1>
            <div className='article__container'>
                {articlesElements}
            </div>
        </div>
        //return Article content
    )
}